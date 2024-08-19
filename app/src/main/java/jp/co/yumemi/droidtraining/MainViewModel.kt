package jp.co.yumemi.droidtraining

import androidx.lifecycle.ViewModel
import jp.co.yumemi.api.YumemiWeather
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val yumemiWeather: YumemiWeather,
) : ViewModel() {
    fun fetchWeather(): String {
        return yumemiWeather.fetchSimpleWeather()
    }
}
