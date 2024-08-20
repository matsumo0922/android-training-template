plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.repository"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
