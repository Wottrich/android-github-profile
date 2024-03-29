import org.gradle.kotlin.dsl.DependencyHandlerScope

object Libs {
    const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    //Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val coroutinesLib =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"

    const val coroutinesTestLib =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    //AppCompat
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    //Koin
    const val insertKoinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
    const val insertKoinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val insertKoinAndroidCompose =
        "io.insert-koin:koin-androidx-compose:${Versions.koinVersion}"

    //Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val placeholder =
        "com.google.accompanist:accompanist-placeholder:${Versions.accompanistVersion}"

    //Compose Navigation
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"

    //Coil
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilVersion}"

    //Api things
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    //Test
    const val mockK = "io.mockk:mockk:${Versions.mockKVersion}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"

}

fun DependencyHandlerScope.defaultClasspath() {
    "classpath"(Libs.gradleVersion)
    "classpath"(Libs.kotlinGradlePlugin)
}

fun DependencyHandlerScope.composeUi() {
    "implementation"(Libs.composeUi)
    "implementation"(Libs.composeUiTooling)
    "implementation"(Libs.composeRuntime)
    "implementation"(Libs.composeMaterial)
    "implementation"(Libs.composeActivity)
    "implementation"(Libs.placeholder)
}

fun DependencyHandlerScope.coroutines() {
    "implementation"(Libs.coroutinesLib)
    "implementation"(Libs.coroutinesTestLib)
}

fun DependencyHandlerScope.koin() {
    "implementation"(Libs.insertKoinCore)
    "implementation"(Libs.insertKoinAndroid)
    "implementation"(Libs.insertKoinAndroidCompose)
}

fun DependencyHandlerScope.unitTest() {
    "testImplementation"(Libs.mockK)
    "testImplementation"(Libs.coreTesting)
    "testImplementation"(Libs.junit)
}

fun DependencyHandlerScope.instrumentalTest() {
    "androidTestImplementation"(Libs.junitExt)
    "androidTestImplementation"(Libs.espressoCore)
}

fun DependencyHandlerScope.apiDigest() {
    "implementation"(Libs.retrofit)
    "implementation"(Libs.converterGson)
    "implementation"(Libs.loggingInterceptor)
}

fun DependencyHandlerScope.room() {
    "implementation"(Libs.roomRuntime)
    "implementation"(Libs.roomKtx)
}