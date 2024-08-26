package primitive

import jp.co.yumemi.droidtraining.android
import jp.co.yumemi.droidtraining.bundle
import jp.co.yumemi.droidtraining.implementation
import jp.co.yumemi.droidtraining.kotlin
import jp.co.yumemi.droidtraining.library
import jp.co.yumemi.droidtraining.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

@OptIn(ExperimentalWasmDsl::class)
class KmpPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")

            android {
                sourceSets {
                    getByName("main") {
                        manifest.srcFile("src/androidMain/AndroidManifest.xml")
                        res.srcDirs("src/androidMain/res")
                    }
                }
            }

            kotlin {
                js(IR) {
                    browser {
                        commonWebpackConfig {
                            outputFileName = "matsumo-me.js"
                        }
                    }

                    binaries.executable()
                }

                wasmJs {
                    browser()
                    binaries.executable()
                }

                androidTarget()
                applyDefaultHierarchyTemplate()

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64(),
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "app"
                        isStatic = true
                    }
                }

                sourceSets.commonMain.dependencies {
                    implementation(project.dependencies.platform(libs.library("koin-bom")))
                    implementation(libs.bundle("koin"))
                }
            }

            dependencies {
                implementation(libs.library("koin-android"))

                add("kspCommonMainMetadata", libs.library("koin-ksp-compiler"))
                add("kspAndroid", libs.library("koin-ksp-compiler"))
                add("kspIosX64", libs.library("koin-ksp-compiler"))
                add("kspIosArm64", libs.library("koin-ksp-compiler"))
                add("kspIosSimulatorArm64", libs.library("koin-ksp-compiler"))
            }
        }
    }
}
