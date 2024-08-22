plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.common"
}

dependencies {
    api(libs.bundles.infra.api)
}
