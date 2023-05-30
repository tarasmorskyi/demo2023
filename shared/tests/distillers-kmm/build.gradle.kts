plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("io.realm.kotlin")
}

version = "1.0"

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":tests:base-kmm"))
                api(project(":distillers-kmm"))
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
    }
}

android {
    namespace = "taras.morskyi.test.distillers_kmm"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}