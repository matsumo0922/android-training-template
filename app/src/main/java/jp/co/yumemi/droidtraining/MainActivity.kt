package jp.co.yumemi.droidtraining

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import jp.co.yumemi.droidtraining.components.MainScreen
import jp.co.yumemi.droidtraining.core.model.ThemeConfig
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.shouldUseDarkTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // For testing purposes, we are using the Light theme
            val themeConfig = ThemeConfig.Light
            val shouldUseDarkTheme = shouldUseDarkTheme(themeConfig)

            val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
            val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

            // LaunchedEffect は Dispatch が必要なので、起動時は DisposableEffect の方が若干早く実行される（らしい）
            // FYI: https://github.com/android/nowinandroid/pull/330/files#r999831539
            DisposableEffect(shouldUseDarkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { shouldUseDarkTheme },
                    navigationBarStyle = SystemBarStyle.auto(lightScrim, darkScrim) { shouldUseDarkTheme },
                )
                onDispose { }
            }

            YumemiTheme(
                themeConfig = themeConfig,
                enableDynamicTheme = false,
            ) {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
