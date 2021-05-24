object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //ui
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //androidX
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentktx}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"

    //dependency injection
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycle}"
    const val hiltLifecycleCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltLifecycle}"

    //networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    //charts
    const val mpAndroidCharts = "com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChart}"
}

object TestLibs {
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val mockk = "io.mockk:mockk:${TestVersions.mockk}"
    const val archCore = "androidx.arch.core:core-testing:${TestVersions.archCore}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${TestVersions.mockWebServer}"
    const val extjUnit = "androidx.test.ext:junit:${TestVersions.extjUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${TestVersions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${TestVersions.espresso}"
    const val hiltTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${TestVersions.uiautomator}"
}