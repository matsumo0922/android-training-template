plugins {
    id("yumemi.primitive.android.application")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining"
}

dependencies {
    implementation(project(":api"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))

    implementation(libs.bundles.ui.implementations)
    implementation(libs.compose.constraint.layout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
