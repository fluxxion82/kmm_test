import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("org.jetbrains.kotlin.kapt")
    id("com.android.library")
}

group = "com.test"
version = "0.0.1"

kotlin {
    kotlin.targets.withType(KotlinNativeTarget::class.java) {
        binaries.all {
            freeCompilerArgs += "-Xbinary=memoryModel=experimental"
        }
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":moduleA"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            }
        }
        val commonTest by getting {
            dependencies {}
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.dagger:hilt-android:2.40.5")
                configurations["kapt"].dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-android-compiler",
                        "2.40.5"
                    )
                )
            }
        }
//        val jvmMain by getting {
//            dependencies {}
//        }
//
//        val jvmTest by getting {
//            dependencies {}
//        }

        val iosMain by getting {
            dependencies {}
        }

        // This is just a workaround to use AS preview
        // https://issuetracker.google.com/issues/217095205
//        val main by creating {
//            dependsOn(jvmMain)
//        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "ModuleB CocoaPods dependencies"
        homepage = "https://github.com/JetBrains/kotlin-native"

        ios.deploymentTarget = "13.5"
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 25
        targetSdk = 32
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
