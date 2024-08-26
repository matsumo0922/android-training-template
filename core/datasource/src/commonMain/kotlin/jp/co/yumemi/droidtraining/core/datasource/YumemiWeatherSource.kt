package jp.co.yumemi.droidtraining.core.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import jp.co.yumemi.droidtraining.core.model.entity.WeatherDetailEntity
import jp.co.yumemi.droidtraining.core.model.entity.WeatherForecastEntity
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single
class YumemiWeatherSource(
    private val client: HttpClient,
    @Provided private val config: YumemiConfig,
) {
    suspend fun fetchWeather(area: Area): WeatherDetailEntity {
        return client.get {
            url("$API/weather")
            parameter("id", area.id)
            defaultParameters()
        }.body()
    }

    suspend fun fetchWeatherForecast(area: Area): WeatherForecastEntity {
        return client.get {
            url("$API/forecast")
            parameter("id", area.id)
            defaultParameters()
        }.body()
    }

    private fun HttpRequestBuilder.defaultParameters() {
        parameter("appid", config.openWeatherMapApiKey)
        parameter("lang", "ja")
        parameter("units", "metric")
    }

    companion object {
        private const val API = "https://api.openweathermap.org/data/2.5"
    }
}
