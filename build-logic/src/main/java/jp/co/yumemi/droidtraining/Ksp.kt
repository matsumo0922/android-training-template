package jp.co.yumemi.droidtraining

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureKsp() {
    extensions.getByType<KspExtension>().apply {
        // for Koin compile time check
        arg("KOIN_CONFIG_CHECK","true")
        arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
    }
}
