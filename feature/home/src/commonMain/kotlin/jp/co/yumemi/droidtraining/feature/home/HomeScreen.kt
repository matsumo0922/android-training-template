package jp.co.yumemi.droidtraining.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.ui.CONTAINER_MAX_WIDTH
import jp.co.yumemi.droidtraining.core.ui.Res
import jp.co.yumemi.droidtraining.core.ui.close
import jp.co.yumemi.droidtraining.core.ui.components.LoadingScreen
import jp.co.yumemi.droidtraining.core.ui.components.SimpleAlertDialog
import jp.co.yumemi.droidtraining.core.ui.error_message_common
import jp.co.yumemi.droidtraining.core.ui.error_title_common
import jp.co.yumemi.droidtraining.core.ui.main_weather_action_reload
import jp.co.yumemi.droidtraining.feature.home.components.MainActionButtonsSection
import jp.co.yumemi.droidtraining.feature.home.components.MainWeatherInfoSection
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun HomeScreen(
    onClickNext: (Area) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = CONTAINER_MAX_WIDTH)
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(if (maxHeight > maxWidth) 80.dp else 16.dp),
            ) {
                if (uiState.weather != null) {
                    MainWeatherInfoSection(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .weight(1f, false),
                        weather = uiState.weather!!,
                    )
                }

                MainActionButtonsSection(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onClickReload = viewModel::reloadWeather,
                    onClickNext = {
                        uiState.weather?.area?.let(onClickNext)
                    },
                )
            }

            AnimatedVisibility(
                modifier = Modifier.fillMaxSize(),
                visible = screenState is HomeWeatherScreenState.Loading,
            ) {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    if (screenState is HomeWeatherScreenState.Error) {
        SimpleAlertDialog(
            title = stringResource(Res.string.error_title_common),
            message = stringResource(Res.string.error_message_common),
            positiveButtonText = stringResource(Res.string.main_weather_action_reload),
            negativeButtonText = stringResource(Res.string.close),
            onPositiveButtonClick = viewModel::reloadWeather,
            onNegativeButtonClick = viewModel::resetScreenState,
            onDismissRequest = viewModel::resetScreenState,
        )
    }
}
