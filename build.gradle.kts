// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext {
//        compose_version = '1.2.0-alpha01'
//        ext.nav_version = "2.4.1"
//        ext.room_version = "2.3.0"
//
//    }
    repositories {
        google()
        mavenCentral()
        maven (url=  Config.ClassPaths.jitPackUrl )
        maven(url = Config.ClassPaths.pluginGradle)

    }
    dependencies {
//        "com.android.tools.build:gradle:7.0.4"
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        classpath(Config.ClassPaths.daggerHiltGradle)
        classpath(Config.ClassPaths.navigationSafArgsGradle)
//        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10'
//        classpath "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
//        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"


    }
}
//plugins {
//    id 'com.android.application' version '7.1.2' apply false
//    id 'com.android.library' version '7.1.2' apply false
//    id 'org.jetbrains.kotlin.android' version '1.6.21' apply false
//    id 'org.jetbrains.kotlin.jvm' version '1.6.21' apply false
//}
//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//        jcenter() // Warning: this repository is going to shut down soon
//    }
//}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}