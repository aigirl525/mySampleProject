package com.example.mysampleproject.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mysampleproject.R
import com.example.mysampleproject.databinding.ActivityLoginBinding
import com.example.mysampleproject.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
class LoginActivity : AppCompatActivity() {

    val viewModel: LoginViewModel by viewModel()
    lateinit var activityLoginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel.register.value = true
        initObserver()
    }

    fun initObserver(){
        viewModel.run {
            //是否注册
            register.observe(this@LoginActivity, Observer {
                register -> (activityLoginBinding.root as MotionLayout).run {
                    if(register){
                        transitionToStart()
                    }else{
                        transitionToEnd()
                    }
                }
            })
            //进度弹窗
            uiCloseData.observe(this@LoginActivity, Observer { close ->
                close?.let {
                    setResult( it.resultCode,it.result)
                    finish()
                }
            })


        }
    }
}