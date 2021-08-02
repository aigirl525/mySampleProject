plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
kapt {
    generateStubs = true
}
android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
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
dependencies {



    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation ("androidx.core:core-ktx:1.2.0")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("com.google.android.material:material:1.1.0")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")

    // SmartRefreshLayout
    implementation(Configuration.Dependencies.smart_refresh)
    implementation(Configuration.Dependencies.smart_refresh_header_classics)
    implementation(Configuration.Dependencies.smart_refresh_footer_classics)

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("com.google.android.material:material:1.2.1")
    api(project(":lib_base"))


}