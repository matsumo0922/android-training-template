package primitive

import jp.co.yumemi.droidtraining.configureDetekt
import jp.co.yumemi.droidtraining.library
import jp.co.yumemi.droidtraining.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            configureDetekt()

            dependencies {
                "detektPlugins"(libs.library("detekt-formatting"))
                "detektPlugins"(libs.library("twitter-compose-rule"))
            }
        }
    }
}
