package jp.co.yumemi.droidtraining.core.repository

import jp.co.yumemi.droidtraining.core.datasource.YumemiWeatherSource
import jp.co.yumemi.droidtraining.core.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

interface YumemiWeatherRepository {
    suspend fun fetchSimpleWeather(): Weather
    suspend fun fetchWeatherAsync(): Weather
    suspend fun fetchThrowsWeather(): Weather
    suspend fun fetchJsonWeather(json: String): Weather
    suspend fun fetchJsonWeatherAsync(json: String): Weather
}

@Single
class YumemiWeatherRepositoryImpl(
    private val yumemiWeatherSource: YumemiWeatherSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YumemiWeatherRepository {

    override suspend fun fetchSimpleWeather(): Weather = withContext(ioDispatcher) {
        Weather.valueOf(yumemiWeatherSource.fetchSimpleWeather())
    }

    override suspend fun fetchWeatherAsync(): Weather = withContext(ioDispatcher) {
        Weather.valueOf(yumemiWeatherSource.fetchWeatherAsync())
    }

    override suspend fun fetchThrowsWeather(): Weather = withContext(ioDispatcher) {
        Weather.valueOf(yumemiWeatherSource.fetchThrowsWeather())
    }

    override suspend fun fetchJsonWeather(json: String): Weather = withContext(ioDispatcher) {
        Weather.valueOf(yumemiWeatherSource.fetchJsonWeather(json))
    }

    override suspend fun fetchJsonWeatherAsync(json: String): Weather = withContext(ioDispatcher) {
        Weather.valueOf(yumemiWeatherSource.fetchJsonWeatherAsync(json))
    }
}
