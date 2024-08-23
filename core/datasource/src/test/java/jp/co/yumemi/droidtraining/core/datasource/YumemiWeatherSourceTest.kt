package jp.co.yumemi.droidtraining.core.datasource

import io.kotest.core.spec.style.FunSpec
import io.kotest.koin.KoinExtension
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import jp.co.yumemi.droidtraining.core.datasource.di.DataSourceModule
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import kotlinx.serialization.json.Json
import org.koin.core.component.get
import org.koin.ksp.generated.module
import org.koin.test.KoinTest

class YumemiWeatherSourceTest : FunSpec(), KoinTest {

    init {
        test("fetchWeather should return expected WeatherDetailEntity") {
            // Arrange
            val response = loadResource("dummy_weather_detail.json")
            val client = setupMockClient(response)
            val source = YumemiWeatherSource(client, YumemiConfig(""))

            // Act
            val result = source.fetchWeather(Area.TOKYO)

            // Assert
            result shouldBe get<Json>().decodeFromString(response)
        }
    }

    override fun extensions() = listOf(KoinExtension(DataSourceModule().module))

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
                headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString())),
            )
        }

        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(get())
            }
        }
    }
}
