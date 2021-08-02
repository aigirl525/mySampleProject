plugins {
    id("com.android.library")
    kotlin("android")
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
}

dependencies {

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation ("androidx.core:core-ktx:1.2.0")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("com.google.android.material:material:1.1.0")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.com.example.lib_base.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")
}