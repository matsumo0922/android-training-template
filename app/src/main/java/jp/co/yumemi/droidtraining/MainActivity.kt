package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import jp.co.yumemi.droidtraining.components.MainScreen
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            YumemiTheme {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
