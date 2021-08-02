package com.example.mysampleproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mysampleproject.R
import com.example.mysampleproject.databinding.AppFragmentHomepageBinding
import com.example.mysampleproject.viewmodel.HomepageFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.lib_ui.base.BR
import com.example.mysampleproject.databinding.SmartRefreshState
/**
 * 主页面 - 首页
 */
class BaseFragment : Fragment(){

     val viewModel: HomepageFragmentViewModel by viewModel()
    var rootView:View? = null
    lateinit var mBinding:AppFragmentHomepageBinding
    /** 标记 - 第一次加载 */
    protected var firstLoad = true
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
//            // 绑定 ViewModel
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
    private fun initView() {
    }

    companion object{
        /** 创建 [BaseFragment] 并返回 */
        fun actionCreate():BaseFragment{
            return BaseFragment()
        }
    }

}