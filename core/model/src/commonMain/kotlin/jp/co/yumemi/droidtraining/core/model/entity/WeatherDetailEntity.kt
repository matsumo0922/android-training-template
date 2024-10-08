package jp.co.yumemi.droidtraining.core.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailEntity(
    @SerialName("base")
    val base: String,
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("cod")
    val cod: Long,
    @SerialName("coord")
    val coord: Coord,
    @SerialName("dt")
    val dt: Long,
    @SerialName("id")
    val id: Long,
    @SerialName("main")
    val main: Main,
    @SerialName("name")
    val name: String,
    @SerialName("rain")
    val rain: Rain?,
    @SerialName("snow")
    val snow: Snow?,
    @SerialName("sys")
    val sys: Sys,
    @SerialName("timezone")
    val timezone: Int,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("weather")
    val weather: List<Weather>,
    @SerialName("wind")
    val wind: Wind,
) {
    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int,
    )

    @Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double,
        @SerialName("lon")
        val lon: Double,
    )

    @Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double,
        @SerialName("grnd_level")
        val grndLevel: Int,
        @SerialName("humidity")
        val humidity: Int,
        @SerialName("pressure")
        val pressure: Int,
        @SerialName("sea_level")
        val seaLevel: Int,
        @SerialName("temp")
        val temp: Double,
        @SerialName("temp_max")
        val tempMax: Double,
        @SerialName("temp_min")
        val tempMin: Double,
    )

    @Serializable
    data class Rain(
        @SerialName("1h")
        val oneHr: Double?,
        @SerialName("3h")
        val threeHr: Double?,
    )

    @Serializable
    data class Snow(
        @SerialName("1h")
        val oneHr: Double?,
        @SerialName("3h")
        val threeHr: Double?,
    )

    @Serializable
    data class Sys(
        @SerialName("country")
        val country: String,
        @SerialName("id")
        val id: Long?,
        @SerialName("sunrise")
        val sunrise: Int,
        @SerialName("sunset")
        val sunset: Int,
        @SerialName("type")
        val type: Int?,
    )

    @Serializable
    data class Weather(
        @SerialName("description")
        val description: String,
        @SerialName("icon")
        val icon: String,
        @SerialName("id")
        val id: Long,
        @SerialName("main")
        val main: String,
    )

    @Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int,
        @SerialName("speed")
        val speed: Double,
    )
}
