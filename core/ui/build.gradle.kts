plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.ui"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.bundles.ui.implementations)
}
