package primitive

import jp.co.yumemi.droidtraining.configureKover
import jp.co.yumemi.droidtraining.isApplicationProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoverPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")

            configureKover()

            if (isApplicationProject()) {
                println("Hello ${project.name}!")
                dependencies {
                    "kover"(project(":api"))
                }
            }
        }
    }
}
