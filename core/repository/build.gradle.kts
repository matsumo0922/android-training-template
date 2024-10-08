plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.kmp")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.repository"

    dependencies {
        testImplementation(libs.bundles.testing)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:common"))
            implementation(project(":core:model"))
            implementation(project(":core:datasource"))
        }
    }
}
