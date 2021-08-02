package com.example.mysampleproject.interfaces.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import cn.wj.common.ext.copy
import cn.wj.common.ext.orElse
import cn.wj.common.ext.orEmpty
import com.example.mysampleproject.constants.NET_PAGE_START
import com.example.mysampleproject.databinding.SmartRefreshState
import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.entity.ArticleListEntity
import com.example.mysampleproject.ext.judge
import com.example.mysampleproject.interfaces.ArticleListPagingInterface
import com.example.mysampleproject.net.NetResult

/**
 * 分页文章列表接口实现类
 *
 * - 创建时间：2020/12/29
 *
 * @author 王杰
 */
class ArticleListPagingInterfaceImpl
    : ArticleListPagingInterface {
    //页码
    override val pageNumber: MutableLiveData<Int> = MutableLiveData()

    /** 文章列表请求返回数据 */
    private val articleListResultData: LiveData<NetResult<ArticleListEntity>> =
        pageNumber.switchMap { pageNum -> getArticleList.invoke(pageNum) }

    //文章列表
    override val articleListData: LiveData<ArrayList<ArticleEntity>> =
        articleListResultData.switchMap { result ->
            disposeArticleListResult(result)
        }

    /** 刷新状态 */
    override val refreshing: MutableLiveData<SmartRefreshState> = MutableLiveData()

    /** 加载更多状态 */
    override val loadMore: MutableLiveData<SmartRefreshState> = MutableLiveData()

    /** 刷新回调 */
    override val onRefresh: () -> Unit = {
        pageNumber.value = NET_PAGE_START
    }

    /** 加载更多回调 */
    override val onLoadMore: () -> Unit = {
        pageNumber.value = pageNumber.value.orElse(NET_PAGE_START) + 1
    }


    override var getArticleList: (Int) -> LiveData<NetResult<ArticleListEntity>> = {
        throw RuntimeException("Please set your custom method!")
    }

    private fun disposeArticleListResult(result: NetResult<ArticleListEntity>): LiveData<ArrayList<ArticleEntity>> {
        val liveData = MutableLiveData<ArrayList<ArticleEntity>>()
        val refresh = pageNumber.value == NET_PAGE_START
        val smartControl = if (refresh) refreshing else loadMore
        result.judge(
            onSuccess = {
                smartControl.value = SmartRefreshState(loading = false, success = true, noMore = data?.over.toBoolean())
                liveData.value = articleListData.value.copy(data?.datas, refresh)
            },
            onFailed = {
                smartControl.value = SmartRefreshState(loading = false, success = false)
                liveData.value = articleListData.value.orEmpty()
            },
            onFailed4Login = {
                smartControl.value = SmartRefreshState(loading = false, success = false)
                liveData.value = articleListData.value.orEmpty()
                false
            }
        )

        return liveData
    }
}