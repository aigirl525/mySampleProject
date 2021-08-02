package com.example.mysampleproject.ext

import com.example.lib_base.ext.string
import com.example.mysampleproject.R
import java.net.ConnectException
import java.net.SocketTimeoutException

/** 异常对应的错误信息 */
val Throwable.hintMsg: String
    get() = when (this) {
        is SocketTimeoutException -> R.string.app_net_error_timeout
        is ConnectException -> R.string.app_net_error_connect
        else -> R.string.app_net_error_failed
    }.string