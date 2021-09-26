import org.gradle.api.JavaVersion

object AndroidSdk {
    const val targetSdk = 31
    const val minSdk = 21
    const val buildToolsVersion = "30.0.3"
    val javaVersion = JavaVersion.VERSION_1_8
    val jvmTarget = JavaVersion.VERSION_1_8
}