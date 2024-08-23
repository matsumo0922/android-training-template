plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining.feature.home"
}

dependencies {
    implementation(project(":core:repository"))
    implementation(project(":core:datasource"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.ui.implementations)
    implementation(libs.compose.constraint.layout)
}
