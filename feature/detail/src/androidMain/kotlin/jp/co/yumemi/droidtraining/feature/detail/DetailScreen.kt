package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.ui.components.LoadingScreen
import jp.co.yumemi.droidtraining.core.ui.components.SimpleAlertDialog
import jp.co.yumemi.droidtraining.feature.detail.components.DetailIdleContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DetailScreen(
    area: Area,
    terminate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = koinViewModel {
        parametersOf(area)
    },
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    AnimatedContent(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        targetState = screenState,
        transitionSpec = { fadeIn().togetherWith(fadeOut()) },
        contentKey = { it::class.simpleName },
        label = "DetailScreen",
    ) { state ->
        when (state) {
            is DetailScreenState.Idle -> {
                DetailIdleContent(
                    modifier = Modifier.fillMaxSize(),
                    uiState = state.uiState,
                    onClickBack = terminate,
                    onClickWeather = { /* not implemented */ },
                )
            }

            is DetailScreenState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            }

            is DetailScreenState.Error -> {
                SimpleAlertDialog(
                    title = stringResource(R.string.error_title_common),
                    message = stringResource(R.string.error_message_common),
                    positiveButtonText = stringResource(R.string.main_weather_action_reload),
                    negativeButtonText = stringResource(R.string.close),
                    onPositiveButtonClick = viewModel::fetch,
                    onNegativeButtonClick = terminate,
                    onDismissRequest = terminate,
                )
            }
        }
    }
}
