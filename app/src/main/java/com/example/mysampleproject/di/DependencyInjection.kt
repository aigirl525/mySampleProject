package com.example.mysampleproject.di

import com.example.mysampleproject.adapter.ArticleListRvAdapter
import com.example.mysampleproject.net.WebService
import com.example.mysampleproject.repository.ArticleRepository
import com.example.mysampleproject.repository.UserRepository
import com.example.mysampleproject.tools.jsonDefault
import com.example.mysampleproject.viewmodel.HomepageFragmentViewModel
import com.example.mysampleproject.viewmodel.LoginViewModel
import com.example.mysampleproject.viewmodel.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit


/** 网络请求 Module */
val netModule: Module = module {

    single{
        //缓存路径
//        val logger = object : HttpLoggingInterceptor{
//
//        }
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)


        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(logging)
            .build()
//            .cookieJar()
//            .addNetworkInterceptor()

    }
    single<Retrofit>{
            Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
//                .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF-8".toMediaType()))
                .addConverterFactory(jsonDefault.asConverterFactory("application/json; charset=UTF-8".toMediaType()))

                .client(get())
                .build()
    }
    single<WebService> {
        get<Retrofit>().create(WebService::class.java)
    }
}
/** 数据仓库 Module */
val repositoryModule:Module = module {
    factory { UserRepository(get()) }
    factory { ArticleRepository(get()) }
}
/** 适配器 Module */
val adapterModule: Module = module {
    factory { ArticleListRvAdapter() }
}
/** ViewModel Module */
val viewModelModule: Module = module {
    viewModel{LoginViewModel(get())}
    viewModel{MainViewModel()}
    viewModel{HomepageFragmentViewModel(get())}
}