package primitive

import jp.co.yumemi.droidtraining.androidLibrary
import jp.co.yumemi.droidtraining.libs
import jp.co.yumemi.droidtraining.setupAndroid
import jp.co.yumemi.droidtraining.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
                apply("com.google.devtools.ksp")
            }

            androidLibrary {
                setupAndroid()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true
            }
        }
    }
}
