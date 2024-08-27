plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.kmp")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.common"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.infra.api)
        }
    }
}
