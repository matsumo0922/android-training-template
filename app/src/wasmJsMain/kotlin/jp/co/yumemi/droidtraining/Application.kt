package jp.co.yumemi.droidtraining

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import jp.co.yumemi.droidtraining.di.applyModules
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        applyModules()
    }

    Napier.base(DebugAntilog())

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        YumemiApp(
            modifier = Modifier.fillMaxSize(),
            themeConfig = ThemeConfig.Light,
        )
    }
}
