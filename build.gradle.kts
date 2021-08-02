// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version = "1.4.21"
    repositories {
        google()
        jcenter()
        maven {setUrl("https://dl.google.com/dl/android/maven2/")}

        maven {setUrl("http://maven.aliyun.com/nexus/content/groups/public/")}
        maven {setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter")}
        maven {setUrl("https://jitpack.io")}
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath(kotlin(module = "gradle-plugin", version = "1.4.21"))

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {setUrl("https://dl.google.com/dl/android/maven2/")}

        maven {setUrl("http://maven.aliyun.com/nexus/content/groups/public/")}
        maven {setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter")}
        maven {setUrl("https://jitpack.io")}
    }
}

tasks.register("clean", Delete::class) {  // #3
    delete(rootProject.buildDir)
}