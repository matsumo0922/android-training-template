package jp.co.yumemi.droidtraining

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import org.koin.compose.KoinContext

@Composable
fun YumemiApp(
    themeConfig: ThemeConfig,
    modifier: Modifier = Modifier,
) {
    KoinContext {
        YumemiTheme(
            themeConfig = themeConfig,
        ) {
            YumemiNavHost(
                modifier = modifier.background(MaterialTheme.colorScheme.surface),
            )
        }
    }
}
