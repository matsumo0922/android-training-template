@file:OptIn(ExperimentalWasmDsl::class)

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
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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
                configureKmpAndroid()
                configureKmpIos()
                configureKmpWasm()

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

private fun KotlinMultiplatformExtension.configureKmpAndroid() {
    androidTarget()
    applyDefaultHierarchyTemplate()
}

private fun KotlinMultiplatformExtension.configureKmpIos() {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.apply {
            binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    }
}

private fun KotlinMultiplatformExtension.configureKmpWasm() {
    wasmJs {
        moduleName = "app"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
}
