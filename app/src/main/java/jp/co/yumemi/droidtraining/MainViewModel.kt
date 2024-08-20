package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
import jp.co.yumemi.droidtraining.core.datasource.YumemiWeather
import jp.co.yumemi.droidtraining.core.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val yumemiWeather: YumemiWeather,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainWeatherUiState())
    private val _screenState = MutableStateFlow<MainWeatherScreenState>(MainWeatherScreenState.Idle)

    val uiState = _uiState.asStateFlow()
    val screenState = _screenState.asStateFlow()

    fun reloadWeather() {
        viewModelScope.launch {
            _screenState.value = MainWeatherScreenState.Loading
            _screenState.value = suspendRunCatching {
                // TODO: daichi-matsumoto 2024/08/20 move to Repository
                val text = withContext(Dispatchers.IO) { yumemiWeather.fetchWeatherAsync() }
                val weather = Weather.fromString(text)

                _uiState.value = MainWeatherUiState(
                    weather = weather,
                )
            }.fold(
                onSuccess = { MainWeatherScreenState.Idle },
                onFailure = { MainWeatherScreenState.Error },
            )
        }
    }

    fun resetScreenState() {
        viewModelScope.launch {
            _screenState.value = MainWeatherScreenState.Idle
        }
    }
}

@Stable
data class MainWeatherUiState(
    val weather: Weather = Weather.Sunny,
)

@Stable
sealed interface MainWeatherScreenState {
    data object Idle : MainWeatherScreenState
    data object Loading : MainWeatherScreenState
    data object Error : MainWeatherScreenState
}
