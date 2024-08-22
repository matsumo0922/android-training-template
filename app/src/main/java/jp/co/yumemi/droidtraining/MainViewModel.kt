package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.repository.YumemiWeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val weatherRepository: YumemiWeatherRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainWeatherUiState())
    private val _screenState = MutableStateFlow<MainWeatherScreenState>(MainWeatherScreenState.Idle)

    val uiState = _uiState.asStateFlow()
    val screenState = _screenState.asStateFlow()

    fun reloadWeather(currentArea: Area?) {
        viewModelScope.launch {
            _screenState.value = MainWeatherScreenState.Loading
            _screenState.value = suspendRunCatching {
                _uiState.value = MainWeatherUiState(
                    weather = weatherRepository.fetchWeather(currentArea ?: Area.TOKYO),
                )
            }.fold(
                onSuccess = { MainWeatherScreenState.Idle },
                onFailure = { MainWeatherScreenState.Error },
            )
        }
    }

    fun nextWeather(currentArea: Area?) {
        viewModelScope.launch {
            _screenState.value = MainWeatherScreenState.Loading
            _screenState.value = suspendRunCatching {
                _uiState.value = MainWeatherUiState(
                    weather = weatherRepository.fetchWeather(currentArea?.let { Area.next(it) } ?: Area.TOKYO),
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
    val weather: WeatherDetail? = null,
)

@Stable
sealed interface MainWeatherScreenState {
    data object Idle : MainWeatherScreenState
    data object Loading : MainWeatherScreenState
    data object Error : MainWeatherScreenState
}
