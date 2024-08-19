package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
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
            suspendRunCatching { yumemiWeather.fetchSimpleWeather() }.onSuccess { weather ->
                _uiState.value = uiState.value.copy(weather = weather)
            }
        }
    }
}

@Stable
data class MainWeatherUiState(
    val weather: String = "",
)
