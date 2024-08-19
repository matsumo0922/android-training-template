package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
import jp.co.yumemi.droidtraining.core.model.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val yumemiWeather: YumemiWeather,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainWeatherUiState())
    val uiState = _uiState.asStateFlow()

    fun reloadWeather() {
        viewModelScope.launch {
            suspendRunCatching {
                val text = yumemiWeather.fetchSimpleWeather()
                val weather = Weather.fromString(text)

                MainWeatherUiState(
                    weather = weather
                )
            }.onSuccess {
                _uiState.value = it
            }
        }
    }
}

@Stable
data class MainWeatherUiState(
    val weather: Weather = Weather.Sunny,
)
