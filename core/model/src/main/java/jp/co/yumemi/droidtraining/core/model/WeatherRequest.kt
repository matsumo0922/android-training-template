package jp.co.yumemi.droidtraining.core.model

import jp.co.yumemi.droidtraining.core.common.serializer.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class WeatherRequest(
    val area: String,
    @Serializable(with = InstantSerializer::class)
    val date: Instant,
)
