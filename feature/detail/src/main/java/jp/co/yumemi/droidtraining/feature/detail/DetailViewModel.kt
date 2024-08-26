package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DetailViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<DetailScreenState>(DetailScreenState.Idle(DetailUiState))

    val screenState = _screenState.asStateFlow()
}

@Stable
data object DetailUiState

@Stable
sealed interface DetailScreenState {
    data class Idle(val uiState: DetailUiState) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}
