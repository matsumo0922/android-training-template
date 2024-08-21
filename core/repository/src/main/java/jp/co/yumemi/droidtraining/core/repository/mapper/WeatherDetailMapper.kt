package jp.co.yumemi.droidtraining.core.repository.mapper

import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.entity.WeatherDetailEntity
import kotlinx.datetime.Instant
import org.koin.core.annotation.Single

@Single
class WeatherDetailMapper {

    fun asWeatherDetail(entity: WeatherDetailEntity): WeatherDetail {
        return WeatherDetail(
            weather = asWeatherType(entity.weather.first()),
            maxTemp = entity.main.tempMax,
            minTemp = entity.main.tempMin,
            date = Instant.fromEpochSeconds(entity.dt, 0),
            area = entity.name
        )
    }

    private fun asWeatherType(entity: WeatherDetailEntity.Weather): Weather {
        return when (entity.id) {
            in 500 until 600 -> Weather.Rainy
            in 600 until 700 -> Weather.Snowy
            in 801 until 900 -> Weather.Cloudy
            800L -> Weather.Sunny
            else -> Weather.Unknown
        }
    }
}
