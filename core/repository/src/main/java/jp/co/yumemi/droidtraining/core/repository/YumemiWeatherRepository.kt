package jp.co.yumemi.droidtraining.core.repository

import jp.co.yumemi.droidtraining.core.datasource.DummyWeatherWrapperSource
import jp.co.yumemi.droidtraining.core.model.WeatherRequest
import jp.co.yumemi.droidtraining.core.model.WeatherResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

interface YumemiWeatherRepository {
    suspend fun fetchWeather(request: WeatherRequest): WeatherResponse
}

@Single
class YumemiWeatherRepositoryImpl(
    private val weatherSource: DummyWeatherWrapperSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YumemiWeatherRepository {

    override suspend fun fetchWeather(request: WeatherRequest): WeatherResponse = withContext(ioDispatcher) {
        weatherSource.fetchWeather(request)
    }
}
