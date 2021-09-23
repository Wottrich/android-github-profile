plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "wottrich.github.io.githubprofile"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.javaVersion
        targetCompatibility = AndroidSdk.javaVersion
    }

    kotlinOptions {
        jvmTarget = AndroidSdk.javaVersion.toString()
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
        kotlinCompilerVersion = Versions.kotlinVersion
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

}


dependencies {
    //Kotlin
    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidCoreKtx)

    //AppCompat and UI things
    implementation(Libs.appCompat)

    //Koin
    koin()

    //Compose
    composeUi()

    //Coil
    implementation(Libs.coilCompose)

    //Retrofit and api things
    implementation(Libs.retrofit)
    implementation(Libs.converterGson)
    implementation(Libs.loggingInterceptor)

    //Modules
    implementation(project(path = ":ui"))

    //File
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Test
    test()

}