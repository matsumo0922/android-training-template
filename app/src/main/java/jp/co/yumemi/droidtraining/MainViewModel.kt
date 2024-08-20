package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
import jp.co.yumemi.droidtraining.core.datasource.YumemiWeather
import jp.co.yumemi.droidtraining.core.model.Weather
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val yumemiWeather: YumemiWeather,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainWeatherUiState())
    private val _viewEvent = Channel<MainWeatherViewEvent>(Channel.BUFFERED)

    val uiState = _uiState.asStateFlow()
    val viewEvent = _viewEvent.receiveAsFlow()

    fun reloadWeather() {
        viewModelScope.launch {
            suspendRunCatching {
                val text = yumemiWeather.fetchThrowsWeather()
                val weather = Weather.fromString(text)

                MainWeatherUiState(
                    weather = weather,
                )
            }.onSuccess {
                _uiState.value = it
            }.onFailure {
                _viewEvent.send(MainWeatherViewEvent.ShowError)
            }
        }
    }

    fun resetViewEvent() {
        viewModelScope.launch {
            _viewEvent.send(MainWeatherViewEvent.Idle)
        }
    }
}

@Stable
data class MainWeatherUiState(
    val weather: Weather = Weather.Sunny,
)

@Stable
sealed interface MainWeatherViewEvent {
    data object Idle : MainWeatherViewEvent
    data object ShowError : MainWeatherViewEvent
}
