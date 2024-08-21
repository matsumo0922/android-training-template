package jp.co.yumemi.droidtraining.core.datasource

import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.WeatherRequest
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class DummyWeatherWrapperSource(
    private val dummyWeatherSource: DummyWeatherSource,
    private val formatter: Json,
) {
    suspend fun fetchWeather(request: WeatherRequest): WeatherDetail {
        val requestJson = formatter.encodeToString(WeatherRequest.serializer(), request)
        val resultJson = dummyWeatherSource.fetchJsonWeatherAsync(requestJson)

        return formatter.decodeFromString(resultJson)
    }
}
