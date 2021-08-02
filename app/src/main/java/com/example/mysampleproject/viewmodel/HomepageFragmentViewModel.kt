package com.example.mysampleproject.viewmodel

import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.wj.common.ext.copy
import cn.wj.common.ext.orEmpty
import com.example.mysampleproject.constants.NET_PAGE_START
import com.example.mysampleproject.databinding.SmartRefreshState
import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.entity.ArticleListEntity
import com.example.mysampleproject.entity.BannerEntity
import com.example.mysampleproject.ext.judge
import com.example.mysampleproject.interfaces.ArticleListPagingInterface
import com.example.mysampleproject.interfaces.impl.ArticleListPagingInterfaceImpl
import com.example.mysampleproject.net.NetResult
import com.example.mysampleproject.repository.ArticleRepository
import kotlinx.coroutines.launch

class HomepageFragmentViewModel(
        private val repository: ArticleRepository
    ) : ViewModel(),
        ArticleListPagingInterface by ArticleListPagingInterfaceImpl() {
    /** 列表事件 */
//    val articleListItemInterface: ArticleListItemInterface by lazy {
//        ArticleListItemInterfaceImpl(this, jumpWebViewData)
//    }

    /** 处理文章列表返回数据 [result]，并返回文章列表 */
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
    init {
        getArticleList = { pageNumber ->
            val result = MutableLiveData<NetResult<ArticleListEntity>>()
            viewModelScope.launch {
                try {
                    result.value = repository.getHomepageArticleList(pageNumber)
                }catch (throwable:Throwable){
                }
            }
            result
        }
    }
    /**
     * 标题折叠监听
     */
    val onOffsetChanged : (Int ,Int) -> Unit = {offset,_ ->
        if(offset == 0){
            //完全展开
            startCarousel()
        }else{
            stopCarousel()
        }
    }
    /**
     * Banner 下标
     */
    val bannerCurrent:ObservableInt = ObservableInt()
    /**
     * Banner 预加载页数
     */
    val bannerLimit : ObservableInt = ObservableInt()
    /**
     *Banner数量
     */
    var bannerCount : ObservableInt = ObservableInt()
    /**
     * Banner触摸事件
     */
    val onBannerTouch : (MotionEvent) -> Boolean = {event ->
        when(event.action){
           MotionEvent.ACTION_DOWN,MotionEvent.ACTION_MOVE -> {
               // 按下、移动，取消轮播
               stopCarousel()
           }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL ->{
                // 抬起、取消，开启轮播
                startCarousel()
            }

        }
         false
    }
    /**
     * 菜单列表点击
     */
    val onMenuItemClick:(MenuItem) -> Boolean = { item ->
        true
    }
    /** 开启 Banner 轮播 */
    fun startCarousel() {

    }
    /** 关闭 Banner 轮播 */
    fun stopCarousel() {

    }
    /** Banner 列表数据 */
    val bannerData: MutableLiveData<List<BannerEntity>> = MutableLiveData()
    fun getHomepageBannerList() {
       viewModelScope.launch {
           try{

               //获取Banner数据
               repository.getHomepageBannerList().judge(onSuccess = {

                 //请求成功
                   bannerData.value = data.orEmpty()

               }
               ,onFailed = {
                   })
           }catch (throwble:Throwable){

           }
       }
    }

    /** 标记 - 问答是否展开 */
    val qaExtend : ObservableBoolean = ObservableBoolean(false)
    /** 问答点击 */
    val onQaClick:() -> Unit = {

    }
    /** Banner 列表条目点击 */
    val onBannerItemClick: (BannerEntity) -> Unit = { item ->
        // 跳转 WebView 打开
//        jumpWebViewData.value = WebViewActivity.ActionModel(item.id.orEmpty(), item.title.orEmpty(), item.url.orEmpty())
    }
}