package jp.co.yumemi.droidtraining.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import jp.co.yumemi.droidtraining.core.ui.colors.DarkBlueColorScheme
import jp.co.yumemi.droidtraining.core.ui.colors.LightBlueColorScheme

@Composable
fun YumemiTheme(
    themeConfig: ThemeConfig = ThemeConfig.System,
    content: @Composable () -> Unit,
) {
    val shouldUseDarkTheme = shouldUseDarkTheme(themeConfig)
    val colorScheme = if (shouldUseDarkTheme) DarkBlueColorScheme else LightBlueColorScheme

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
