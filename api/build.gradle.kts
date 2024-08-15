@file:Suppress("UnstableApiUsage")

plugins {
    id("yumemi.primitive.android.library")
    id("yumemi.primitive.detekt")
}

android {
    namespace = "jp.co.yumemi.api"
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines)

    // Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockk)
}
