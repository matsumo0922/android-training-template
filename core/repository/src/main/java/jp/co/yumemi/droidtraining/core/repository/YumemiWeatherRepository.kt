package jp.co.yumemi.droidtraining.core.repository

import jp.co.yumemi.droidtraining.core.datasource.DummyWeatherWrapperSource
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.WeatherRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

interface YumemiWeatherRepository {
    suspend fun fetchWeather(request: WeatherRequest): WeatherDetail
}

@Single
class YumemiWeatherRepositoryImpl(
    private val weatherSource: DummyWeatherWrapperSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YumemiWeatherRepository {

    override suspend fun fetchWeather(request: WeatherRequest): WeatherDetail = withContext(ioDispatcher) {
        weatherSource.fetchWeather(request)
    }
}
