package jp.co.yumemi.droidtraining.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.components.LoadingScreen
import jp.co.yumemi.droidtraining.core.ui.components.SimpleAlertDialog
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews
import jp.co.yumemi.droidtraining.feature.home.components.MainActionButtonsSection
import jp.co.yumemi.droidtraining.feature.home.components.MainWeatherInfoSection
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    var lastClickedButtonType by remember { mutableStateOf(ButtonType.RELOAD) }

    Scaffold(modifier) {
        Box {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                val (weatherInfoSection, actionButtonsSection) = createRefs()

                if (uiState.weather != null) {
                    MainWeatherInfoSection(
                        modifier = Modifier.constrainAs(weatherInfoSection) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)

                            width = Dimension.percent(0.5f)
                            height = Dimension.wrapContent
                        },
                        weather = uiState.weather!!,
                    )
                }

                MainActionButtonsSection(
                    modifier = Modifier.constrainAs(actionButtonsSection) {
                        if (uiState.weather != null) {
                            top.linkTo(weatherInfoSection.bottom, 80.dp)
                            start.linkTo(weatherInfoSection.start)
                            end.linkTo(weatherInfoSection.end)

                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        } else {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)

                            width = Dimension.percent(0.5f)
                            height = Dimension.wrapContent
                        }
                    },
                    onClickReload = {
                        lastClickedButtonType = ButtonType.RELOAD
                        viewModel.reloadWeather(uiState.weather?.area)
                    },
                    onClickNext = {
                        lastClickedButtonType = ButtonType.NEXT
                        viewModel.nextWeather(uiState.weather?.area)
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
            title = stringResource(R.string.error_title_common),
            message = stringResource(R.string.error_message_common),
            positiveButtonText = stringResource(R.string.main_weather_action_reload),
            negativeButtonText = stringResource(R.string.close),
            onPositiveButtonClick = {
                when (lastClickedButtonType) {
                    ButtonType.RELOAD -> viewModel.reloadWeather(uiState.weather?.area)
                    ButtonType.NEXT -> viewModel.nextWeather(uiState.weather?.area)
                }
            },
            onNegativeButtonClick = viewModel::resetScreenState,
            onDismissRequest = viewModel::resetScreenState,
        )
    }
}

private enum class ButtonType {
    RELOAD, NEXT
}

@ComponentPreviews
@Composable
private fun MainScreenPreview() {
    YumemiTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
