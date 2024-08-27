plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.kmp")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    namespace = "jp.co.yumemi.droidtraining.feature.detail"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:repository"))
            implementation(project(":core:datasource"))
            implementation(project(":core:ui"))
            implementation(project(":core:model"))
            implementation(project(":core:common"))

            implementation(libs.coil3.compose)
        }

        wasmJsMain.dependencies {
            implementation(libs.coil3.wasm)
        }
    }
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "false")
}
