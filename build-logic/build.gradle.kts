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
    }
}
