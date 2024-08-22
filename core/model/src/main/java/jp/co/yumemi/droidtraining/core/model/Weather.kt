package jp.co.yumemi.droidtraining.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Weather {
    Sunny,
    Cloudy,
    Rainy,
    Snowy,
    Unknown,
}
