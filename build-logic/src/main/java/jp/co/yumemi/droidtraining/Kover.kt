package jp.co.yumemi.droidtraining

import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureKover() {
    extensions.getByType<KoverProjectExtension>().apply {
        reports {
            filters {
                includes.classes("jp.co.yumemi.*")
                excludes.classes("*BuildConfig*")
            }
        }
    }
}
