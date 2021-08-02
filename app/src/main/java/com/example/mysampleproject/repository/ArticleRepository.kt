package com.example.mysampleproject.repository

import cn.wj.common.ext.orFalse
import com.example.mysampleproject.constants.NET_PAGE_START
import com.example.mysampleproject.constants.STR_TRUE
import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.ext.netRequest
import com.example.mysampleproject.net.WebService
import kotlinx.coroutines.async

class ArticleRepository (private val webService: WebService){
    /** 获取首页 Banner 列表 */
    suspend fun getHomepageBannerList() = netRequest {
        webService.getHomepageBannerList()
    }

    /**
     * 根据页码[pageNum]获取首页文章列表
     * > [pageNum] 为 [NET_PAGE_START] 时，将同时获取置顶数据合并返回
     */
    suspend fun getHomepageArticleList(pageNum: Int) = netRequest {
        // 获取文章列表
        val ls = arrayListOf<ArticleEntity>()
        if (pageNum == NET_PAGE_START) {
            // 刷新获取置顶文章列表
            val tops = async {
                webService.getHomepageTopArticleList().data.orEmpty()
            }
            tops.await().forEach {
                ls.add(it.copy(top = STR_TRUE))
            }
        }
        // 获取文章列表
        val resultAsync = async {
            webService.getHomepageArticleList(pageNum)
        }
        val result = resultAsync.await()
        // 添加文章列表到 ls
        ls.addAll(result.data?.datas.orEmpty())
        // 处理收藏状态
        ls.forEach {
            it.collected.set(it.collect?.toBoolean().orFalse())
        }
        // 处理返回列表
        result.copy(data = result.data?.copy(datas = ls))
    }
}