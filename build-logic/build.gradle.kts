plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "yumemi.primitive.android.application"
            implementationClass = "primitive.ApplicationPlugin"
        }
        register("AndroidLibrary") {
            id = "yumemi.primitive.android.library"
            implementationClass = "primitive.LibraryPlugin"
        }
        register("DetektPlugin") {
            id = "yumemi.primitive.detekt"
            implementationClass = "primitive.DetektPlugin"
        }
        register("KoverPlugin") {
            id = "yumemi.primitive.kover"
            implementationClass = "primitive.KoverPlugin"
        }
    }
}
