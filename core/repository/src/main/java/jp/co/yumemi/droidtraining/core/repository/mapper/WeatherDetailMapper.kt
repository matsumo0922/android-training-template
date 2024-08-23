package jp.co.yumemi.droidtraining.core.repository.mapper

import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import jp.co.yumemi.droidtraining.core.model.entity.WeatherDetailEntity
import jp.co.yumemi.droidtraining.core.model.entity.WeatherForecastEntity
import kotlinx.datetime.Instant
import org.koin.core.annotation.Single

@Single
class WeatherDetailMapper {

    fun asWeatherDetail(entity: WeatherDetailEntity): WeatherDetail {
        return WeatherDetail(
            weather = asWeatherType(entity.weather.first().id),
            maxTemp = entity.main.tempMax,
            minTemp = entity.main.tempMin,
            date = Instant.fromEpochSeconds(entity.dt, 0),
            area = Area.fromId(entity.id),
            areaName = entity.name,
        )
    }

    fun asWeatherForecast(entity: WeatherForecastEntity): WeatherForecast {
        return WeatherForecast(
            area = Area.fromId(entity.city.id),
            areaName = entity.city.name,
            dayWeathers = entity.list.map {
                WeatherForecast.DayWeather(
                    weather = asWeatherType(it.weather.first().id),
                    iconUrl = "https://openweathermap.org/img/wn/${it.weather.first().icon}@4x.png",
                    maxTemp = it.main.tempMax.toFloat(),
                    minTemp = it.main.tempMin.toFloat(),
                    date = Instant.fromEpochSeconds(it.dt, 0),
                    rain = it.rain?.oneHr?.toFloat() ?: it.rain?.threeHr?.toFloat(),
                    snow = it.snow?.oneHr?.toFloat() ?: it.snow?.threeHr?.toFloat(),
                )
            }
        )
    }

    private fun asWeatherType(id: Long): Weather {
        return when (id) {
            in 500 until 600 -> Weather.Rainy
            in 600 until 700 -> Weather.Snowy
            in 801 until 900 -> Weather.Cloudy
            800L -> Weather.Sunny
            else -> Weather.Unknown
        }
    }
}
