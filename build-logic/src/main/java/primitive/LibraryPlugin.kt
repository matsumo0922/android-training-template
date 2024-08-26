package primitive

import jp.co.yumemi.droidtraining.androidLibrary
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

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlinx-serialization")
                apply("com.google.devtools.ksp")
            }

            androidLibrary {
                setupAndroid()
                configureKsp()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true
            }
        }
    }
}
