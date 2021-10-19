plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinLibrary)
}

java {
    sourceCompatibility = AndroidSdk.javaVersion
    targetCompatibility = AndroidSdk.javaVersion
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(Libs.kotlinStdlib)
    api(Libs.androidCoreKtx)
    api(Libs.appCompat)
    api(Libs.composeNavigation)
    api(project(":ui"))
    api(project(":base"))
    api(project(":screenstate"))
    api(project(":profilerepositorysharedcomponents"))
    api(project(":datasource"))
    api(project(":resource"))
    coroutines()
    koin()
    composeUi()
}