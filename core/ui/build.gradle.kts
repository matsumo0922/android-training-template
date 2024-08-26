plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.kmp")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.ui"

    dependencies {
        implementation(libs.bundles.ui.implementations)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:common"))
            implementation(project(":core:model"))

            api(compose.runtime)
            api(compose.runtimeSaveable)
            api(compose.foundation)
            api(compose.animation)
            api(compose.animationGraphics)
            api(compose.material)
            api(compose.material3)
            api(compose.ui)
            api(compose.materialIconsExtended)
            api(compose.components.resources)
        }
    }
}
