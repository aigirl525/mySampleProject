package com.example.mysampleproject.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel(){

    val onBackClick : () -> Unit = {

    }
    val showFingerprint : ObservableBoolean = ObservableBoolean(true)
    val fingerprintChecked : ObservableBoolean = ObservableBoolean(true)

    /** 退出登录点击 */
    val onLogoutClick: () -> Unit = {
//        showLogoutData.value = 0
    }
}