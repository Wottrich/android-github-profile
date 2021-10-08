plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = AndroidSdk.targetSdk

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk

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
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidCoreKtx)
    implementation(Libs.appCompat)
    implementation(project(":ui"))
    implementation(project(":base"))
    implementation(project(":screenstate"))
    implementation(project(":profilerepositorysharedcomponents"))
    implementation(project(":datasource"))
    implementation(project(":resource"))
    coroutines()
    koin()
    composeUi()
    unitTest()
    instrumentalTest()
}