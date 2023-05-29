plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

version = "1.0"

val testDependencies: List<Any> = rootProject.ext.get("testDependencies") as List<Any>
val testKotlinDependencies: List<String> =
    rootProject.ext.get("testKotlinDependencies") as List<String>

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":base-kmm"))
                testDependencies.forEach {
                    api(it)
                }
                testKotlinDependencies.forEach {
                    api(kotlin(it))
                }
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(libs.junit4)
            }
        }
    }
}

android {
    namespace = "taras.morskyi.tests.base_kmm"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}