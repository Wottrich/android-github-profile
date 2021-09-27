plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidSdk.targetSdk
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        applicationId = "wottrich.github.io.githubprofile"
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        resources.excludes.add("META-INF/*")
//        exclude("META-INF/DEPENDENCIES")
//        exclude("META-INF/LICENSE")
//        exclude("META-INF/LICENSE.txt")
//        exclude("META-INF/license.txt")
//        exclude("META-INF/NOTICE")
//        exclude("META-INF/NOTICE.txt")
//        exclude("META-INF/notice.txt")
//        exclude("META-INF/ASL2.0")
//        exclude("META-INF/*.kotlin_module")
    }

}


dependencies {
    //Kotlin
    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidCoreKtx)
    coroutines()

    //AppCompat and UI things
    implementation(Libs.appCompat)

    //Koin
    koin()

    //Compose
    composeUi()
    implementation(Libs.composeNavigation)

    //Coil
    implementation(Libs.coilCompose)

    //Modules
    implementation(project(path = ":ui"))
    implementation(project(path = ":datasource"))

    //File
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Test
    unitTest()
    instrumentalTest()

}