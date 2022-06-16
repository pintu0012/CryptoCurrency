plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
//    id(Config.Plugins.androidLibrary)
//    'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//    id 'kotlin-kapt'
//    id 'dagger.hilt.android.plugin'
//    id 'androidx.navigation.safeargs.kotlin'

}

android {
//    compileSdk 32
    compileSdkVersion(Config.Android.androidCompileSdkVersion)


//    composeOptions {
//        kotlinCompilerExtensionVersion (Versions.composeVersion)
////        kotlinCompilerVersion '1.5.30'
//    }
//    dataBinding {
//        enabled = true
//    }

    defaultConfig {
        applicationId = (Environments.Release.appId)
        minSdk = (Config.Android.androidMinSdkVersion)
        targetSdk = (Config.Android.androidTargetSdkVersion)
        versionCode = (Environments.Release.appVersionCode)
        versionName = (Environments.Release.appVersionName)

        testInstrumentationRunner = (Config.testRunner)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    //Modules
    implementation(project(Modules.cache))

    //Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.navigationFragmentKtx)
    implementation(UiDep.navigationUiKtx)
    implementation(UiDep.activityKtx)
    //Lifecycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }
    //Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }
    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    //Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }

    //Glide
    implementation(UiDep.glide)
    kapt(UiDep.glideKapt)
    //Timber
    implementation(UiDep.timber)


    // Test Dependencies
    testImplementation(UiDep.Test.junit)
    testImplementation(UiDep.Test.assertJ)
    testImplementation(UiDep.Test.mockitoKotlin)
    testImplementation(UiDep.Test.mockitoInline)
    testImplementation(UiDep.Test.coroutines)
    testImplementation(UiDep.Test.androidxArchCore)
    testImplementation(UiDep.Test.robolectric)
    testImplementation(UiDep.Test.testExtJunit)

//    implementation "androidx.legacy:legacy-support-v4:1.0.0"
//    testImplementation "junit:junit:4.13.2"
//    androidTestImplementation "androidx.test.ext:junit:1.1.3"
//    androidTestImplementation "androidx.test.espresso:espresso-core:3.4.0"

    // Compose dependencies
//    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
//    implementation "androidx.navigation:navigation-compose:2.4.0-alpha08"
//    implementation "com.google.accompanist:accompanist-flowlayout:0.17.0"
//    implementation "androidx.compose.material:material:1.1.1"

//    // Coroutine Lifecycle Scopes
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
//    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

    //Dagger - Hilt
//    implementation "com.google.dagger:hilt-android:2.38.1"
//    kapt "com.google.dagger:hilt-android-compiler:2.37"
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
//    kapt "androidx.hilt:hilt-compiler:1.0.0"
//    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0-alpha03'

    // Retrofit
    RemoteDep.retrofit.forEach {
        implementation(it)
    }
//    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
//    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
//    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
//    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"

    //FlexBoxLayout
    implementation(UiDep.flexBoxLayout)
//    implementation 'com.google.android.flexbox:flexbox:3.0.0'

}