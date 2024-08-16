package jp.co.yumemi.droidtraining

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.commonExt(action: CommonExtension<*, *, *, *, *, *>.() -> Unit) {
    val plugin = if (isApplicationProject()) BaseAppModuleExtension::class.java else LibraryExtension::class.java
    (this as ExtensionAware).extensions.configure(plugin, action)
}

fun Project.setupAndroid() {
    android {
        defaultConfig {
            targetSdk = libs.version("targetSdk").toInt()
            minSdk = libs.version("minSdk").toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
        }
    }
}

fun Project.isApplicationProject(): Boolean {
    return project.extensions.findByType(BaseAppModuleExtension::class.java) != null
}

fun Project.isLibraryProject(): Boolean {
    return project.extensions.findByType(LibraryExtension::class.java) != null
}
