package jp.co.yumemi.droidtraining.core.repository

import jp.co.yumemi.droidtraining.core.datasource.YumemiWeatherSource
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import jp.co.yumemi.droidtraining.core.repository.mapper.WeatherDetailMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

interface YumemiWeatherRepository {
    suspend fun fetchWeather(area: Area): WeatherDetail
    suspend fun fetchWeatherForecast(area: Area): WeatherForecast
}

@Single
class YumemiWeatherRepositoryImpl(
    private val weatherSource: YumemiWeatherSource,
    private val weatherDetailMapper: WeatherDetailMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : YumemiWeatherRepository {

    override suspend fun fetchWeather(area: Area): WeatherDetail = withContext(ioDispatcher) {
        val entity = weatherSource.fetchWeather(area)
        weatherDetailMapper.asWeatherDetail(entity)
    }

    override suspend fun fetchWeatherForecast(area: Area): WeatherForecast = withContext(ioDispatcher) {
        val entity = weatherSource.fetchWeatherForecast(area)
        weatherDetailMapper.asWeatherForecast(entity)
    }
}
