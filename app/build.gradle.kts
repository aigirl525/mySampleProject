plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.4.21"
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId ="com.example.mysampleproject"
        minSdkVersion (21)
        targetSdkVersion (30)
        versionCode =1
        versionName ="1.0"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        // DataBinding 开启
        dataBinding = true
    }



}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}

dependencies {
    // Kotlin
    implementation(Configuration.Dependencies.kotlin_stdlib)
    // 协程
    implementation(Configuration.Dependencies.kotlin_coroutines)
    // Json 序列化
    implementation(Configuration.Dependencies.kotlin_serialization)

    // Dex 分包
    implementation(Configuration.Dependencies.androidx_multidex)

    // v4
    implementation(Configuration.Dependencies.androidx_legacy)
    // v7
    implementation(Configuration.Dependencies.androidx_appcompat)
    // design
    implementation(Configuration.Dependencies.androidx_material)
    // RecyclerView
    implementation(Configuration.Dependencies.androidx_recyclerview)
    // 约束性布局
    implementation(Configuration.Dependencies.androidx_constraint)

    // activity
    implementation(Configuration.Dependencies.androidx_activity_ktx)
    // fragment
    implementation(Configuration.Dependencies.androidx_fragment_ktx)

    // ktx
    implementation(Configuration.Dependencies.androidx_core_ktx)

    // LifeCycle 拓展
    implementation(Configuration.Dependencies.androidx_lifecycle_ktx)
    implementation(Configuration.Dependencies.androidx_lifecycle_extensions)
    // ViewModel 拓展
    implementation(Configuration.Dependencies.androidx_lifecycle_viewmodel_ktx)
    // LiveData 拓展
    implementation(Configuration.Dependencies.androidx_lifecycle_livedata_ktx)

    // viewpager2
    implementation(Configuration.Dependencies.androidx_viewpager2)

    // Logger
    implementation(Configuration.Dependencies.logger)

    // Koin
    implementation(Configuration.Dependencies.koin_scope)
    implementation(Configuration.Dependencies.koin_viewmodel)
    implementation(Configuration.Dependencies.koin_ext)

    // LiveEventBus
    implementation(Configuration.Dependencies.live_event_bus)

    // OkHttp
    implementation(Configuration.Dependencies.okhttp)
    implementation(Configuration.Dependencies.okhttp_logging)

    // Retrofit
    implementation(Configuration.Dependencies.retrofit)
    implementation(Configuration.Dependencies.retrofit_converter_kt)

    // Glide
    implementation(Configuration.Dependencies.coil)

    // MMKV 数据存储
    implementation(Configuration.Dependencies.tencent_mmkv)

    implementation(Configuration.Dependencies.arouter_api)
    kapt(Configuration.Dependencies.arouter_compiler)

    // SmartRefreshLayout
    implementation(Configuration.Dependencies.smart_refresh)
    implementation(Configuration.Dependencies.smart_refresh_header_classics)
    implementation(Configuration.Dependencies.smart_refresh_footer_classics)

    // 状态栏工具
    implementation(Configuration.Dependencies.immersion_bar)
    implementation(Configuration.Dependencies.immersion_bar_ktx)

    // 换肤
    implementation(Configuration.Dependencies.skin_support)
    implementation(Configuration.Dependencies.skin_support_appcompat)
    implementation(Configuration.Dependencies.skin_support_material)
    implementation(Configuration.Dependencies.skin_support_cardview)
    implementation(Configuration.Dependencies.skin_support_constraint)

    // Tablayout
    implementation(Configuration.Dependencies.tablayout)

    implementation (project( ":lib_ui"))
    implementation (project( ":lib_views_custom"))
    implementation (project( ":lib_base"))
    implementation (project( ":lib_databinding_adapter"))
    implementation (project( ":lib_views_custom"))
    implementation (project( ":lib_recyclerview"))

    // 测试
    testImplementation(Configuration.Dependencies.test_junit)
    androidTestImplementation(Configuration.Dependencies.androidx_test_runner)
    androidTestImplementation(Configuration.Dependencies.androidx_test_espresso_core)
    androidTestImplementation(Configuration.Dependencies.androidx_test_ext_junit)

}