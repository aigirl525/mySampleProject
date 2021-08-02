package com.example.mysampleproject.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.lib_ui.viewmodel.BaseLibViewModel
import com.example.mysampleproject.model.SnackbarModel
import com.example.mysampleproject.model.UiCloseModel

open class BaseViewModel : BaseLibViewModel(){
    /** Snackbar 控制 */
    val snackbarData = MutableLiveData<SnackbarModel>()

    /** 控制 UI 组件关闭 */
    val uiCloseData = MutableLiveData<UiCloseModel>()

    /** 界面跳转控制 */
    val uiNavigationData = MutableLiveData<String>()
}