
import com.android.build.api.variant.BuildConfigField
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.Serializable

plugins {
    id("yumemi.primitive.android.application")
    id("yumemi.primitive.detekt")
    id("yumemi.primitive.kover")
    id("yumemi.primitive.compose")
}

android {
    val file = project.rootDir.resolve("local.properties")
    val localProperties = Properties().apply {
        if (file.exists()) load(file.inputStream())
    }

    namespace = "jp.co.yumemi.droidtraining"

    androidComponents {
        onVariants {
            it.buildConfigFields.putBuildConfig(localProperties, "OPEN_WEATHER_MAP_API_KEY")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))

    implementation(project(":core:repository"))
    implementation(project(":core:datasource"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.ui.implementations)
    implementation(libs.compose.constraint.layout)

    androidTestImplementation(platform(libs.koin.bom))
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.android.testing)
}

fun MapProperty<String, BuildConfigField<out Serializable>>.putBuildConfig(
    localProperties: Properties,
    key: String,
    value: String? = null,
    type: String = "String",
    defaultValue: String = "",
    comment: String? = null,
) {
    val property = localProperties.getProperty(key)
    val env = System.getenv(key)

    put(key, BuildConfigField(type, (value ?: property ?: env ?: defaultValue).toStringLiteral(), comment))
}

fun Any.toStringLiteral(): String {
    val value = toString()
    return if (value.firstOrNull() == '\"' && value.lastOrNull() == '\"') value else "\"$value\""
}
