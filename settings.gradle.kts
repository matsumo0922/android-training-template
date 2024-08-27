@file:Suppress("UnstableApiUsage")

rootProject.name = "android-traning-template"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

include(":app")
include(":core:datasource")
include(":core:ui")
include(":core:model")
include(":core:common")
include(":core:repository")
include(":feature:home")
include(":feature:detail")
