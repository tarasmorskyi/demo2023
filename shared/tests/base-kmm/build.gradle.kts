plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":base-kmm"))
                api(libs.kotlinx.coroutines.test)
                api(libs.turbine)
                api(libs.koin.test)
                api(libs.kotlin.test.junit)
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
    }
}

android {
    namespace = "taras.morskyi.tests.base_kmm"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}