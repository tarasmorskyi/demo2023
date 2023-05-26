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
                api(kotlin("stdlib-common"))
                api(libs.realm.library.base)
                api(libs.koin.core)
                api(libs.ktor.client.core)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.client.logging)
                api(libs.ktor.client.serialization)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.kotlinx.coroutines.core)
                api(libs.napier)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.ktor.client.okhttp)
                api(libs.androidx.lifecycle.livedata.ktx)
                api(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }
//        val iosMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.ios)
//            }
//        }
    }
}

android {
    namespace = "taras.morskyi.base_kmm"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}