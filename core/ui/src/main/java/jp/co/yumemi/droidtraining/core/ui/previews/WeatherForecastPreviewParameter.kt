package jp.co.yumemi.droidtraining.core.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import kotlinx.datetime.Instant

class WeatherForecastPreviewParameter: PreviewParameterProvider<WeatherForecast> {

    override val values: Sequence<WeatherForecast>
        get() = sequenceOf(dummy)

    val dummy = WeatherForecast(
        area = Area.TOKYO,
        areaName = "Tokyo",
        dayWeathers = listOf(
            WeatherForecast.DayWeather(
                weather = Weather.Sunny,
                iconUrl = "https://openweathermap.org/img/wn/10d@4x.png",
                maxTemp = 32f,
                minTemp = 21f,
                date = Instant.parse("2022-08-16T06:05:00Z"),
                rain = 35f,
                snow = null,
            ),
            WeatherForecast.DayWeather(
                weather = Weather.Cloudy,
                iconUrl = "https://openweathermap.org/img/wn/11d@4x.png",
                maxTemp = 28f,
                minTemp = 16f,
                date = Instant.parse("2022-08-17T06:25:00Z"),
                rain = 50f,
                snow = null,
            ),
        ),
    )
}
