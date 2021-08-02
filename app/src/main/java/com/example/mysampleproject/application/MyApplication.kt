package com.example.mysampleproject.application

import android.app.Application
import android.app.Person
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.example.mysampleproject.di.adapterModule
import com.example.mysampleproject.di.netModule
import com.example.mysampleproject.di.repositoryModule
import com.example.mysampleproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.app.SkinLayoutInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import java.util.logging.Logger

class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())
            .addInflater(SkinMaterialViewInflater())
            .addInflater(SkinConstraintViewInflater())
            .addInflater(SkinCardViewInflater())
            .loadSkin()

        startKoin(){
            androidContext(this@MyApplication)
            modules(listOf(netModule, viewModelModule, repositoryModule, adapterModule))//这里面传各种被注入的模块对象，支持多模块注入
        }
    }

}