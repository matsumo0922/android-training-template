package jp.co.yumemi.droidtraining.core.datasource

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import kotlinx.serialization.json.Json
import org.koin.test.KoinTest
import org.koin.test.inject

class YumemiWeatherSourceTest : FunSpec(), KoinTest {

    private val formatter by inject<Json>()
    private val config by inject<YumemiConfig>()

    init {
        test("fetchWeather should return expected WeatherDetailEntity") {
            // Arrange
            val response = loadResource("dummy_weather_detail.json")
            val client = setupMockClient(response)
            val source = YumemiWeatherSource(client, config)

            // Act
            val result = source.fetchWeather(Area.TOKYO)

            // Assert
            result shouldBe formatter.decodeFromString(response)
        }
    }

    private fun loadResource(fileName: String): String {
        return ClassLoader.getSystemResourceAsStream(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    private fun setupMockClient(response: String): HttpClient {
        val engine = MockEngine {
            respond(
                content = response,
                status = HttpStatusCode.OK,
                headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
            )
        }

        return HttpClient(engine)
    }
}
