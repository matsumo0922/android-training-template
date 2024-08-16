package primitive

import jp.co.yumemi.droidtraining.androidTestImplementation
import jp.co.yumemi.droidtraining.commonExt
import jp.co.yumemi.droidtraining.debugImplementation
import jp.co.yumemi.droidtraining.implementation
import jp.co.yumemi.droidtraining.library
import jp.co.yumemi.droidtraining.libs
import jp.co.yumemi.droidtraining.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugin("compose-compiler").pluginId)

            commonExt {
                buildFeatures.compose = true
            }

            dependencies {
                val bom = libs.library("compose-bom")

                implementation(project.dependencies.platform(bom))
                implementation(libs.library("compose-ui-tooling-preview"))
                debugImplementation(libs.library("compose-ui-tooling"))
                androidTestImplementation(project.dependencies.platform(bom))
            }
        }
    }
}
