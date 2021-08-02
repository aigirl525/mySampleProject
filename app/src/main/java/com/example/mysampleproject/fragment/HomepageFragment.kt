package com.example.mysampleproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lib_recyclerview.adapter.simple.SimpleRvAdapter
import com.example.lib_recyclerview.layoutmanager.WrapContentLinearLayoutManager
import com.example.mysampleproject.R
import com.example.mysampleproject.adapter.ArticleListRvAdapter
import com.example.mysampleproject.databinding.AppFragmentHomepageBinding
import com.example.mysampleproject.databinding.SmartRefreshState
import com.example.mysampleproject.entity.BannerEntity
import com.example.mysampleproject.viewmodel.HomepageFragmentViewModel
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.lib_base.BR
/**
 * 主页面 - 首页
 */
class HomepageFragment : Fragment(){

     val viewModel: HomepageFragmentViewModel by viewModel()

    var rootView:View? = null
    lateinit var mBinding:AppFragmentHomepageBinding
    /** 标记 - 第一次加载 */
    protected var firstLoad = true
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化观察者
        initObserve()
    }
    /** Banner 列表适配器 */
    private val mBannerAdapter = SimpleRvAdapter<BannerEntity>(R.layout.app_viewpager_item_banner)
    private fun initObserve() {
       viewModel.run {
            //banner列表
           bannerData.observe(this@HomepageFragment,{list ->
               // 更新 Banner 列表
               mBannerAdapter.refresh(list)
               // 设置 Banner 数量并开启轮播
               bannerCount.set(list.size)
           })
           // 文章列表
           articleListData.observe(this@HomepageFragment, {
               // 更新文章列表
               mArticlesAdapter.submitList(it)
           })
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null){

            // 初始化 DataBinding
            mBinding = DataBindingUtil.inflate(inflater, R.layout.app_fragment_homepage, container, false)
            //databing的生命周期也是与Activity一致
            mBinding.lifecycleOwner = this
            // 绑定 ViewModel
            mBinding.setVariable(BR.viewModel, viewModel)
            rootView = mBinding.root
            //初始化布局
            initView()
        }else{
            (rootView?.parent as? ViewGroup?)?.removeView(rootView)
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        if (firstLoad) {
            // 获取 Banner 数据
            viewModel.getHomepageBannerList()
            // 刷新文章列表
            viewModel.refreshing.value = SmartRefreshState(true)
        }

    }



    override fun onPause() {
        super.onPause()
        // 标记不是第一次加载
        firstLoad = false
    }
    /** 文章列表适配器 */
    private val mArticlesAdapter: ArticleListRvAdapter by inject()
    private fun initView() {
        // 配置 banner
        mBinding.vpBanner.adapter = mBannerAdapter

        // 配置文章列表
        mBinding.rvArticles.let { rv ->
            rv.layoutManager = WrapContentLinearLayoutManager()
            rv.adapter = mArticlesAdapter.also {
//                it.viewModel = viewModel.articleListItemInterface
//                it.setEmptyView(R.layout.app_layout_placeholder)
                it.showHeaderWhenEmpty(true)
            }
        }
    }

    companion object{
        /** 创建 [HomepageFragment] 并返回 */
        fun actionCreate():HomepageFragment{
            return HomepageFragment()
        }
    }

}