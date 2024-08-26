package jp.co.yumemi.droidtraining.core.repository

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jp.co.yumemi.droidtraining.core.datasource.YumemiWeatherSource
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import jp.co.yumemi.droidtraining.core.model.entity.WeatherDetailEntity
import jp.co.yumemi.droidtraining.core.model.entity.WeatherForecastEntity
import jp.co.yumemi.droidtraining.core.repository.mapper.WeatherDetailMapper
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.koin.test.KoinTest

class YumemiWeatherRepositoryTest : FunSpec(), KoinTest {

    private val weatherDetailMapper = mockk<WeatherDetailMapper>()
    private val weatherSource = mockk<YumemiWeatherSource>()
    private val dummyWeatherDetailEntity = mockk<WeatherDetailEntity>()
    private val dummyWeatherDetail = mockk<WeatherDetail>()
    private val dummyWeatherForecastEntity = mockk<WeatherForecastEntity>()
    private val dummyWeatherForecast = mockk<WeatherForecast>()
    private val testDispatcher = StandardTestDispatcher()

    init {
        test("fetchWeather should call datasource and mapper") {
            // Arrange
            val area = Area.TOKYO
            val repository = YumemiWeatherRepositoryImpl(weatherSource, weatherDetailMapper, testDispatcher)

            coEvery { weatherDetailMapper.asWeatherDetail(dummyWeatherDetailEntity) } returns mockk()
            coEvery { weatherSource.fetchWeather(area) } returns dummyWeatherDetailEntity

            // Act
            runTest(testDispatcher) {
                repository.fetchWeather(area)
            }

            // Assert
            coVerify { weatherDetailMapper.asWeatherDetail(dummyWeatherDetailEntity) }
            coVerify { weatherSource.fetchWeather(area) }
        }

        test("fetchWeather should return the same value as the mapper") {
            // Arrange
            val area = Area.TOKYO
            val repository = YumemiWeatherRepositoryImpl(weatherSource, weatherDetailMapper, testDispatcher)

            coEvery { weatherDetailMapper.asWeatherDetail(dummyWeatherDetailEntity) } returns dummyWeatherDetail
            coEvery { weatherSource.fetchWeather(area) } returns dummyWeatherDetailEntity

            runTest(testDispatcher) {
                // Act
                val result = repository.fetchWeather(area)

                // Assert
                result shouldBe dummyWeatherDetail
            }
        }

        test("fetchWeatherForecast should call datasource and mapper") {
            // Arrange
            val area = Area.TOKYO
            val repository = YumemiWeatherRepositoryImpl(weatherSource, weatherDetailMapper, testDispatcher)

            coEvery { weatherDetailMapper.asWeatherForecast(dummyWeatherForecastEntity) } returns mockk()
            coEvery { weatherSource.fetchWeatherForecast(area) } returns dummyWeatherForecastEntity

            // Act
            runTest(testDispatcher) {
                repository.fetchWeatherForecast(area)
            }

            // Assert
            coVerify { weatherDetailMapper.asWeatherForecast(dummyWeatherForecastEntity) }
            coVerify { weatherSource.fetchWeatherForecast(area) }
        }

        test("fetchWeatherForecast should return the same value as the mapper") {
            // Arrange
            val area = Area.TOKYO
            val repository = YumemiWeatherRepositoryImpl(weatherSource, weatherDetailMapper, testDispatcher)

            coEvery { weatherDetailMapper.asWeatherForecast(dummyWeatherForecastEntity) } returns dummyWeatherForecast
            coEvery { weatherSource.fetchWeatherForecast(area) } returns dummyWeatherForecastEntity

            runTest(testDispatcher) {
                // Act
                val result = repository.fetchWeatherForecast(area)

                // Assert
                result shouldBe dummyWeatherForecast
            }
        }
    }
}
