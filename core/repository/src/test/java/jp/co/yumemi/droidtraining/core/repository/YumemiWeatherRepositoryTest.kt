package jp.co.yumemi.droidtraining.core.repository

import io.kotest.core.spec.style.FunSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jp.co.yumemi.droidtraining.core.datasource.YumemiWeatherSource
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.entity.WeatherDetailEntity
import jp.co.yumemi.droidtraining.core.repository.mapper.WeatherDetailMapper
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.koin.test.KoinTest

class YumemiWeatherRepositoryTest : FunSpec(), KoinTest {

    private val weatherDetailMapper = mockk<WeatherDetailMapper>()
    private val weatherSource = mockk<YumemiWeatherSource>()
    private val dummyWeatherDetailEntity = mockk<WeatherDetailEntity>()
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
    }
}
