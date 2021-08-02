package com.example.mysampleproject.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.example.mysampleproject.R
import com.example.mysampleproject.base.viewmodel.BaseViewModel
import com.example.mysampleproject.constants.*

class MainViewModel : BaseViewModel(){
    /**ViewPage**/
    var currentItem: MutableLiveData<Int> = MutableLiveData<Int>(TAB_MAIN_BOTTOM_HOMEPAGE)

    /** 底部菜单选中回调 **/
    val onItemSelected:(Int) -> Boolean = {itemId ->
        val targetPosition = when(itemId){
            R.id.menu_homepage -> {
                // 首页
                TAB_MAIN_BOTTOM_HOMEPAGE
            }
            R.id.menu_sytem -> {
                // 体系
                TAB_MAIN_BOTTOM_SYSTEM
            }
            R.id.menu_bjnews -> {
                // 公众号
                TAB_MAIN_BOTTOM_BJNEWS
            }
            R.id.menu_project -> {
                // 项目
                TAB_MAIN_BOTTOM_PROJECT
            }
            R.id.menu_my -> {
                // 我的
                TAB_MAIN_BOTTOM_MY
            }
            else -> {
                // 其他，默认首页
                TAB_MAIN_BOTTOM_HOMEPAGE
            }
        }
        if(currentItem.value == targetPosition){
            //不同，切换Fragment
            currentItem.value = (targetPosition)
        }
        true
    }

}