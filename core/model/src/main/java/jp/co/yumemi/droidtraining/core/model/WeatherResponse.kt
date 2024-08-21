package jp.co.yumemi.droidtraining.core.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weather: Weather,
    val maxTemp: Int,
    val minTemp: Int,
    val date: Instant,
    val area: String,
)
