package jp.co.yumemi.droidtraining.core.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class WeatherRequest(
    val area: String,
    val date: Instant,
)
