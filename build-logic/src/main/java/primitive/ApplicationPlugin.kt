package primitive

import jp.co.yumemi.droidtraining.androidApplication
import jp.co.yumemi.droidtraining.bundle
import jp.co.yumemi.droidtraining.configureKsp
import jp.co.yumemi.droidtraining.implementation
import jp.co.yumemi.droidtraining.ksp
import jp.co.yumemi.droidtraining.library
import jp.co.yumemi.droidtraining.libs
import jp.co.yumemi.droidtraining.setupAndroid
import jp.co.yumemi.droidtraining.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
                apply("com.google.devtools.ksp")
            }

            androidApplication {
                setupAndroid()
                configureKsp()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true

                defaultConfig {
                    applicationId = "jp.co.yumemi.droidtraining"

                    versionName = libs.version("versionName")
                    versionCode = libs.version("versionCode").toInt()
                }
            }

            dependencies {
                implementation(project.dependencies.platform(libs.library("koin-bom")))
                implementation(libs.bundle("koin"))
                ksp(libs.library("koin-ksp-compiler"))
            }
        }
    }
}
