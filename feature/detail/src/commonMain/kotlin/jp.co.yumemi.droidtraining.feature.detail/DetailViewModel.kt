package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.droidtraining.core.common.suspendRunCatching
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import jp.co.yumemi.droidtraining.core.repository.YumemiWeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

class DetailViewModel(
    @InjectedParam
    private val area: Area,
    private val weatherRepository: YumemiWeatherRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            _screenState.value = DetailScreenState.Loading
            _screenState.value = suspendRunCatching {
                DetailUiState(
                    weatherForecast = weatherRepository.fetchWeatherForecast(area),
                )
            }.fold(
                onSuccess = { DetailScreenState.Idle(it) },
                onFailure = { DetailScreenState.Error },
            )
        }
    }
}

@Stable
data class DetailUiState(
    val weatherForecast: WeatherForecast,
)

@Stable
sealed interface DetailScreenState {
    data class Idle(val uiState: DetailUiState) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}
