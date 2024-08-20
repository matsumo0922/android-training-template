package jp.co.yumemi.repository.model

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
internal data class WeatherResponse(
    val weather: String,
    val maxTemp: Int,
    val minTemp: Int,
    val date: Date,
    val area: String,
)
