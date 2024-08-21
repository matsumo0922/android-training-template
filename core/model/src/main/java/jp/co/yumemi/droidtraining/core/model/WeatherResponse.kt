package jp.co.yumemi.droidtraining.core.model

import jp.co.yumemi.droidtraining.core.common.serializer.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weather: Weather,
    val maxTemp: Int,
    val minTemp: Int,
    @Serializable(with = InstantSerializer::class)
    val date: Instant,
    val area: String,
)
