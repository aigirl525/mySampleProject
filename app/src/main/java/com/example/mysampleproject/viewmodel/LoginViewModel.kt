package com.example.mysampleproject.viewmodel

import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import cn.wj.common.ext.condition
import com.example.lib_base.ext.string
import com.example.mysampleproject.R
import com.example.mysampleproject.base.viewmodel.BaseViewModel
import com.example.mysampleproject.constants.PASSWORD_MIN_LENGTH
import com.example.mysampleproject.ext.judge
import com.example.mysampleproject.model.UiCloseModel
import com.example.mysampleproject.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : BaseViewModel() {


    /** 密码 */
    val password: ObservableField<String> = ObservableField<String>()

    /** 密码错误文本 */
    val passwordError: ObservableField<String> = object : ObservableField<String>() {
        override fun get(): String? {
            val get = password.get()
            if (!get.isNullOrBlank() && get.length >= PASSWORD_MIN_LENGTH) {
                return ""
            }
            return super.get()
        }
    }

    /** 再次输入密码 */
    val repassword: ObservableField<String> = ObservableField<String>()

    /** 密码错误文本 */
    val repasswordError: ObservableField<String> = object : ObservableField<String>(repassword) {
        override fun get(): String? {
            val get = repassword.get()
            if (!get.isNullOrBlank() && get.length >= PASSWORD_MIN_LENGTH && get == password.get()) {
                return ""
            }
            return super.get()
        }
    }

    /** 标记 - 是否是注册 */
    val register: MutableLiveData<Boolean> = MutableLiveData(true)


    val userNameError: ObservableField<String> = ObservableField()
    val userName: ObservableField<String> = ObservableField()
    val showFingerprint: ObservableBoolean = ObservableBoolean()
    val onCloseClick: (MenuItem) -> Boolean = {
        false
    }

    /**
     * 注册，登陆点击
     */
    val onTabClick: (Boolean) -> Unit = { registerClick ->
        register.value = registerClick
    }

    /** 按钮文本 */
    val buttonStr: LiveData<String> = register.map { isRegister ->
        if (isRegister) {
            // 注册
            R.string.app_register
        } else {
            // 登录
            R.string.app_login
        }.string
    }

    /**
     * 按钮点击
     */
    val onButtonClick: () -> Unit = fun() {
        if (userName.get().isNullOrBlank()) {
            //用户名为空
            userNameError.set(R.string.app_please_enter_user_name.string)
            return
        }
        if (password.get().isNullOrBlank()) {
            //密码为空
            passwordError.set(R.string.app_please_enter_user_name.string)
            return
        }
        if (password.get().orEmpty().length < PASSWORD_MIN_LENGTH) {
            // 密码长度小于最低长度
            passwordError.set(R.string.app_password_length_must_larger_than_six.string)
            return
        }
        if (register.value.condition) {
            // 注册
            if (repassword.get().isNullOrBlank()) {
                // 密码为空
                repasswordError.set(R.string.app_password_must_not_be_empty.string)
                return
            }
            if (repassword.get().orEmpty().length < PASSWORD_MIN_LENGTH) {
                // 密码长度小于最低长度
                repasswordError.set(R.string.app_password_length_must_larger_than_six.string)
                return
            }
            if (repassword.get() != password.get()) {
                // 密码不匹配
                repasswordError.set(R.string.app_re_set_password_not_match.string)
                return
            }
        }

        if (register.value.condition) {
            // 注册
            register()
        } else {
            // 登录
            login()
        }
    }
    val onFingerprintClick: () -> Unit = {

    }

    /** 注册 */
    private fun register() {
        viewModelScope.launch {
            try {
                // 显示进度条弹窗
//                progressData.value = ProgressModel(cancelable = false)
                repository.register(userName.get().orEmpty(), password.get().orEmpty())
                    .judge(onSuccess = {
                        // 注册成功，保存用户信息
//                        UserInfoData.value = data
//                        DATA_CACHE_KEY_USER_NAME.encode(userName.get().orEmpty())
//                        // 关闭当前界面
                        uiCloseData.value = UiCloseModel()
                        Log.e("12345", "success")

                    }, onFailed = {
//                        defaultFaildBlock
                        Log.e("12345", "fail")
                    }
                    )
            } catch (throwable: Throwable) {
                // 打印错误日志
//                Logger.t("NET").e(throwable, "register")
//                snackbarData.value = throwable.toSnackbarModel()
            } finally {
                // 隐藏进度条弹窗
//                progressData.value = null
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            try {
                repository.login(userName.get().orEmpty(), password.get().orEmpty())
                    .judge(onSuccess = {
                        //登录成功，保存用户信息
                        uiCloseData.value = UiCloseModel()
                    }, onFailed = {

                    }
                    )

            } catch (throwable: Throwable) {

            } finally {

            }
        }
    }
}