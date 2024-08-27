package primitive

import jp.co.yumemi.droidtraining.android
import jp.co.yumemi.droidtraining.bundle
import jp.co.yumemi.droidtraining.kotlin
import jp.co.yumemi.droidtraining.library
import jp.co.yumemi.droidtraining.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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
                            outputFileName = "yumemi.js"
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
                        baseName = "ComposeApp"
                        isStatic = true
                    }
                }

                sourceSets.commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

                    dependencies {
                        implementation(project.dependencies.platform(libs.library("koin-bom")))
                        implementation(libs.bundle("koin"))
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.library("koin-ksp-compiler"))
            }

            // WORKAROUND FOR KOIN KSP: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
            tasks.withType<KotlinCompilationTask<*>>().configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}
