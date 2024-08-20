@file:Suppress("MemberVisibilityCanBePrivate")

package jp.co.yumemi.repository

import android.content.Context
import android.os.NetworkOnMainThreadException
import com.squareup.moshi.Moshi
import jp.co.yumemi.repository.model.DateAdapter
import jp.co.yumemi.repository.model.Weather
import jp.co.yumemi.repository.model.WeatherRequest
import jp.co.yumemi.repository.model.WeatherResponse
import kotlinx.coroutines.delay
import org.koin.core.annotation.Single
import java.util.Date
import kotlin.random.Random

@Single
class YumemiWeather(
    private val context: Context,
    private val random: Random = Random.Default,
) {

    fun fetchSimpleWeather(): String = Weather.values().random(random).name.lowercase()

    fun fetchThrowsWeather(): String {
        if ((0..4).random(random) == 4) {
            throw UnknownException()
        }
        return fetchSimpleWeather()
    }

    suspend fun fetchWeatherAsync(): String {
        if (context.mainLooper.isCurrentThread) {
            throw NetworkOnMainThreadException()
        }

        delay(3_000)

        return fetchThrowsWeather()
    }

    fun fetchJsonWeather(json: String): String {
        val moshi = Moshi.Builder()
            .add(Date::class.java, DateAdapter())
            .build()

        val requestAdapter = moshi.adapter(WeatherRequest::class.java)
        val request = requestAdapter.fromJson(json) ?: throw IllegalArgumentException("Failed to parse the JSON into a WeatherRequest object.")

        val responseAdapter = moshi.adapter(WeatherResponse::class.java)

        val maxTemp = (10..40).random(random)
        val minTemp = (-40..maxTemp).random(random)
        val response = WeatherResponse(
            weather = fetchThrowsWeather(),
            maxTemp = maxTemp,
            minTemp = minTemp,
            date = request.date,
            area = request.area,
        )
        return responseAdapter.toJson(response)
    }

    suspend fun fetchJsonWeatherAsync(json: String): String {
        if (context.mainLooper.isCurrentThread) {
            throw NetworkOnMainThreadException()
        }

        delay(3_000)

        return fetchJsonWeather(json)
    }
}

class UnknownException : Throwable()
