plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

group = "com.github.WangJie0822"

android {

    // 编译 SDK 版本
    compileSdkVersion(30)

    // 资源前缀
    resourcePrefix("rv")

    defaultConfig {
        // 最低支持版本
        minSdkVersion(Configuration.AppConfigs.min_sdk_version)
        // 目标 SDK 版本
        targetSdkVersion(Configuration.AppConfigs.target_sdk_version)
    }

    // 源文件路径设置
    sourceSets {
        named("main") {
            java.srcDirs("src/main/java", "src/main/kotlin")
            jni.srcDirs("libs", "jniLibs")
        }
    }

    buildFeatures {
        // DataBinding 开启
        dataBinding = true
    }

    // Java 版本配置
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Kotlin
    implementation(Configuration.Dependencies.kotlin_stdlib)

    // RecyclerView
    implementation(Configuration.Dependencies.androidx_recyclerview)
}
