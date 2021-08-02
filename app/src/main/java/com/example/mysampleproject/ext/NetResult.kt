package com.example.mysampleproject.ext

import android.util.Log
import com.example.lib_base.ext.string
import com.example.lib_base.utils.AppManager
import com.example.mysampleproject.R
import com.example.mysampleproject.activity.LoginActivity
import com.example.mysampleproject.constants.NET_RESPONSE_CODE_LOGIN_FAILED
import com.example.mysampleproject.constants.NET_RESPONSE_CODE_SUCCESS
import com.example.mysampleproject.model.SnackbarModel
import com.example.mysampleproject.net.NetResult
import com.google.android.material.snackbar.Snackbar

inline fun <T> NetResult<T>.judge(
    crossinline onSuccess: NetResult<T>.() -> Unit = {},
    crossinline onFailed: NetResult<T>.() -> Unit = {},
    crossinline onFailed4Login: NetResult<T>.() -> Boolean = { false }
) {
    when (errorCode) {
        NET_RESPONSE_CODE_SUCCESS -> {
            // 请求成功
            onSuccess.invoke(this)
        }
        NET_RESPONSE_CODE_LOGIN_FAILED -> {
            // 登录失效，需要重新登录
            if (onFailed4Login.invoke(this)) {
                // 已消费事件
                return
            }
//            (AppManager.peekActivity() as? BaseActivity<*, *>)?.run {
//                viewModel.snackbarData.value = SnackbarModel(
//                    content = errorMsg,
//                    duration = Snackbar.LENGTH_INDEFINITE,
//                    actionText = R.string.app_go_login.string,
//                    onAction = {
//                        // 登录失败，需要重新登录
//                        LoginActivity.actionStart(this)
//                    }
//                )
//            }
        }
        else -> {
            // 请求失败
            onFailed.invoke(this)
        }
    }
}