package jp.co.yumemi.droidtraining.feature.home

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

class HomeViewModel(
    private val weatherRepository: YumemiWeatherRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeWeatherUiState())
    private val _screenState = MutableStateFlow<HomeWeatherScreenState>(HomeWeatherScreenState.Idle)

    val uiState = _uiState.asStateFlow()
    val screenState = _screenState.asStateFlow()

    fun reloadWeather() {
        viewModelScope.launch {
            _screenState.value = HomeWeatherScreenState.Loading
            _screenState.value = suspendRunCatching {
                _uiState.value = HomeWeatherUiState(
                    weather = weatherRepository.fetchWeather(Area.entries.random()),
                )
            }.fold(
                onSuccess = { HomeWeatherScreenState.Idle },
                onFailure = { HomeWeatherScreenState.Error },
            )
        }
    }

    fun resetScreenState() {
        viewModelScope.launch {
            _screenState.value = HomeWeatherScreenState.Idle
        }
    }
}

@Stable
data class HomeWeatherUiState(
    val weather: WeatherDetail? = null,
)

@Stable
sealed interface HomeWeatherScreenState {
    data object Idle : HomeWeatherScreenState
    data object Loading : HomeWeatherScreenState
    data object Error : HomeWeatherScreenState
}
