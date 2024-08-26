package primitive

import jp.co.yumemi.droidtraining.android
import jp.co.yumemi.droidtraining.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
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
            }
        }
    }
}
