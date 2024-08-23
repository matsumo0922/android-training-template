package jp.co.yumemi.droidtraining.core.model

import jp.co.yumemi.droidtraining.core.common.serializer.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val area: Area,
    val areaName: String,
    val dayWeathers: List<DayWeather>,
) {
    @Serializable
    data class DayWeather(
        val weather: Weather,
        val iconUrl: String,
        val maxTemp: Float,
        val minTemp: Float,
        @Serializable(with = InstantSerializer::class)
        val date: Instant,
        val rain: Float?,
        val snow: Float?,
    )
}
