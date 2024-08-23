package jp.co.yumemi.droidtraining.core.model.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastEntity(
    @SerialName("city")
    val city: City,
    @SerialName("cnt")
    val cnt: Int,
    @SerialName("cod")
    val cod: String,
    @SerialName("list")
    val list: List<DayWeather>,
    @SerialName("message")
    val message: Int
) {
    @Serializable
    data class City(
        @SerialName("coord")
        val coord: Coord,
        @SerialName("country")
        val country: String,
        @SerialName("id")
        val id: Long,
        @SerialName("name")
        val name: String,
        @SerialName("population")
        val population: Int,
        @SerialName("sunrise")
        val sunrise: Int,
        @SerialName("sunset")
        val sunset: Int,
        @SerialName("timezone")
        val timezone: Int
    ) {
        @Serializable
        data class Coord(
            @SerialName("lat")
            val lat: Double,
            @SerialName("lon")
            val lon: Double
        )
    }

    @Serializable
    data class DayWeather(
        @SerialName("clouds")
        val clouds: Clouds,
        @SerialName("dt")
        val dt: Long,
        @SerialName("dt_txt")
        val dtTxt: String,
        @SerialName("main")
        val main: Main,
        @SerialName("pop")
        val pop: Double,
        @SerialName("rain")
        val rain: Rain?,
        @SerialName("snow")
        val snow: Snow?,
        @SerialName("visibility")
        val visibility: Int,
        @SerialName("weather")
        val weather: List<Weather>,
        @SerialName("wind")
        val wind: Wind
    ) {
        @Serializable
        data class Clouds(
            @SerialName("all")
            val all: Int
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
            @SerialName("temp_kf")
            val tempKf: Double,
            @SerialName("temp_max")
            val tempMax: Double,
            @SerialName("temp_min")
            val tempMin: Double
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
        data class Weather(
            @SerialName("description")
            val description: String,
            @SerialName("icon")
            val icon: String,
            @SerialName("id")
            val id: Long,
            @SerialName("main")
            val main: String
        )

        @Serializable
        data class Wind(
            @SerialName("deg")
            val deg: Int,
            @SerialName("gust")
            val gust: Double,
            @SerialName("speed")
            val speed: Double
        )
    }
}
