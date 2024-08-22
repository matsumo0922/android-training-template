@file:Suppress("UnstableApiUsage")

plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
}

android {
    namespace = "jp.co.yumemi.droidtraining.core.datasource"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.kotlinx.coroutines)

    // Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // Ktor
    implementation(libs.bundles.ktor)

    // Test
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
