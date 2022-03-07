import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("org.jetbrains.kotlin.kapt")
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
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("co.touchlab:stately-common:1.2.0")
                implementation("co.touchlab:stately-concurrency:1.2.0")
                implementation("co.touchlab:stately-iso-collections:1.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("org.assertj:assertj-core:3.21.0")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:1.6.10")
            }
        }
        val jvmMain by getting {
            dependencies {}
        }
        val jvmTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {}
        }
        val iosTest by getting {
            dependencies {}
        }

        // This is just a workaround to use AS preview
        // https://issuetracker.google.com/issues/217095205
        val main by creating {
            dependsOn(jvmMain)
        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "moduleA CocoaPods dependencies"
        homepage = "https://github.com/JetBrains/kotlin-native"

        ios.deploymentTarget = "13.5"
    }
}
