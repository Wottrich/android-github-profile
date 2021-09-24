import org.gradle.api.JavaVersion

object AndroidSdk {
    const val targetSdk = 31
    const val minSdk = 21
    val javaVersion = JavaVersion.VERSION_1_8
    val jvmTarget = JavaVersion.VERSION_1_8
}