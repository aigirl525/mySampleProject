package com.example.mysampleproject.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mysampleproject.R
import com.example.mysampleproject.constants.TAB_MAIN_BOTTOM_HOMEPAGE
import com.example.mysampleproject.databinding.ActivityMainBinding
import com.example.mysampleproject.fragment.HomepageFragment
import com.example.mysampleproject.fragment.MyFragment
import com.example.mysampleproject.simple.setFragmentAdapter
import com.example.mysampleproject.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
     val viewModel:MainViewModel by viewModel()
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 配置适配器
        activityMainBinding.vpMain.setFragmentAdapter(this){
           count(5)
            createFragment { position ->
                when(position){
                    TAB_MAIN_BOTTOM_HOMEPAGE -> HomepageFragment.actionCreate()
//                    TAB_MAIN_BOTTOM_SYSTEM ->
//                    TAB_MAIN_BOTTOM_BJNEWS ->
//                    TAB_MAIN_BOTTOM_PROJECT ->
                    else -> MyFragment.actionCreate()
                }

            }
        }
        viewModel.currentItem.value = 2
    }
}