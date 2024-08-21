package jp.co.yumemi.droidtraining.core.repository

import jp.co.yumemi.droidtraining.core.datasource.YumemiWeatherSource
import jp.co.yumemi.droidtraining.core.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

interface YumemiWeatherRepository {
    suspend fun fetchWeather(): Weather
}

@Single
class YumemiWeatherRepositoryImpl(
    private val weatherSource: YumemiWeatherSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YumemiWeatherRepository {

    override suspend fun fetchWeather(): Weather = withContext(ioDispatcher) {
        Weather.valueOf(weatherSource.fetchThrowsWeather())
    }
}
