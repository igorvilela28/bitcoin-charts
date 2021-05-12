
object Libs {
    const val kotlin =  "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val coreKtx =  "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat =  "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material =  "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =  "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

}

object TestLibs {
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val extjUnit = "androidx.test.ext:junit:${TestVersions.extjUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${TestVersions.espressoCore}"
}