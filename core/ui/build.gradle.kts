@file:Suppress("UnusedPrivateProperty")

plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.kmp")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.ui"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:common"))
                implementation(project(":core:model"))

                api(compose.runtime)
                api(compose.runtimeSaveable)
                api(compose.foundation)
                api(compose.animation)
                api(compose.animationGraphics)
                api(compose.material)
                api(compose.material3)
                api(compose.ui)
                api(compose.materialIconsExtended)
                api(compose.components.resources)

                api(libs.kmp.navigation.compose)
                api(libs.kmp.lifecycle.runtime.compose)
                api(libs.kmp.lifecycle.viewmodel.compose)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "jp.co.yumemi.droidtraining.core.ui"
    generateResClass = always
}
