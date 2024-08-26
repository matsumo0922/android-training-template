package jp.co.yumemi.droidtraining.core.ui

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import jp.co.yumemi.droidtraining.core.ui.colors.DarkBlueColorScheme
import jp.co.yumemi.droidtraining.core.ui.colors.LightBlueColorScheme

@Composable
fun YumemiTheme(
    themeConfig: ThemeConfig = ThemeConfig.System,
    enableDynamicTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val shouldUseDarkTheme = shouldUseDarkTheme(themeConfig)

    val colorScheme = when {
        enableDynamicTheme && supportsDynamicTheming() -> if (shouldUseDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        else -> if (shouldUseDarkTheme) DarkBlueColorScheme else LightBlueColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = YumemiTypography,
        shapes = YumemiShapes,
        content = content,
    )
}

@Composable
fun shouldUseDarkTheme(themeConfig: ThemeConfig): Boolean {
    return when (themeConfig) {
        ThemeConfig.System -> isSystemInDarkTheme()
        ThemeConfig.Light -> false
        ThemeConfig.Dark -> true
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun supportsDynamicTheming(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}
