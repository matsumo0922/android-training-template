@file:Suppress("UnstableApiUsage")

rootProject.name = "android-traning-template"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
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
