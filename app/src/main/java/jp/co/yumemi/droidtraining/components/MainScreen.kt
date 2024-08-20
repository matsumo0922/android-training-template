package jp.co.yumemi.droidtraining.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.co.yumemi.droidtraining.MainWeatherUiState
import jp.co.yumemi.droidtraining.MainWeatherViewEvent
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.components.SimpleAlertDialog
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainScreen(
    uiState: MainWeatherUiState,
    viewEvent: MainWeatherViewEvent,
    onResetViewEvent: () -> Unit,
    onClickReload: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isErrorDisplayed by remember { mutableStateOf(false) }

    LaunchedEffect(viewEvent) {
        when (viewEvent) {
            MainWeatherViewEvent.Idle -> Unit
            MainWeatherViewEvent.ShowError -> {
                isErrorDisplayed = true
            }
        }
    }

    Scaffold(modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            val (weatherInfoSection, actionButtonsSection) = createRefs()

            MainWeatherInfoSection(
                modifier = Modifier.constrainAs(weatherInfoSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.percent(0.5f)
                    height = Dimension.wrapContent
                },
                weather = uiState.weather,
            )

            MainActionButtonsSection(
                modifier = Modifier.constrainAs(actionButtonsSection) {
                    top.linkTo(weatherInfoSection.bottom, 80.dp)
                    start.linkTo(weatherInfoSection.start)
                    end.linkTo(weatherInfoSection.end)

                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                onClickReload = onClickReload,
                onClickNext = onClickNext,
            )
        }
    }

    if (isErrorDisplayed) {
        SimpleAlertDialog(
            title = stringResource(R.string.error_title_common),
            message = stringResource(R.string.error_message_common),
            positiveButtonText = stringResource(R.string.main_weather_action_reload),
            negativeButtonText = stringResource(R.string.close),
            onPositiveButtonClick = {
                onClickReload.invoke()
            },
            onDismissRequest = {
                onResetViewEvent.invoke()
                isErrorDisplayed = false
            },
        )
    }
}

@ComponentPreviews
@Composable
private fun MainScreenPreview() {
    YumemiTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = MainWeatherUiState(weather = Weather.Snowy),
            viewEvent = MainWeatherViewEvent.Idle,
            onResetViewEvent = {},
            onClickReload = {},
            onClickNext = {},
        )
    }
}
