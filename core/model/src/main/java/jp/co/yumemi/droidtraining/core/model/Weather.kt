package jp.co.yumemi.droidtraining.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Weather {
    @SerialName("sunny") Sunny,
    @SerialName("cloudy") Cloudy,
    @SerialName("rainy") Rainy,
    @SerialName("snow") Snowy;
}
