package jp.co.yumemi.droidtraining

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import org.koin.compose.KoinContext

fun MainViewController() = ComposeUIViewController {
    KoinContext {
        YumemiApp(
            modifier = Modifier.fillMaxSize(),
            themeConfig = ThemeConfig.Light,
        )
    }
}
