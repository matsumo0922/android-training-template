plugins {
    id("yumemi.primitive.android.application")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining"
}

dependencies {
    implementation(project(":api"))

    implementation(libs.bundles.ui.implementations)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
