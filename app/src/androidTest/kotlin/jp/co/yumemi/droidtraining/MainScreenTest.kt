package jp.co.yumemi.droidtraining

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.coEvery
import io.mockk.mockk
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.repository.YumemiWeatherRepository
import jp.co.yumemi.droidtraining.di.applyModules
import jp.co.yumemi.droidtraining.feature.home.HomeScreen
import jp.co.yumemi.droidtraining.feature.home.HomeViewModel
import kotlinx.datetime.Instant
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

class MainScreenTest : KoinTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val weatherRepository = mockk<YumemiWeatherRepository>()
    private val viewModel = HomeViewModel(weatherRepository)

    private val instrumentationContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val application = instrumentationContext.applicationContext as Application

    private val dummyWeatherDetail1 = WeatherDetail(
        area = Area.TOKYO,
        areaName = "Tokyo",
        weather = Weather.Cloudy,
        maxTemp = 36.8,
        minTemp = 21.2,
        date = Instant.parse("2022-01-01T00:00:00Z"),
    )

    private val dummyWeatherDetail2 = WeatherDetail(
        area = Area.NAGOYA,
        areaName = "Nagoya",
        weather = Weather.Sunny,
        maxTemp = 30.0,
        minTemp = 15.0,
        date = Instant.parse("2022-01-01T00:00:00Z"),
    )

    @Before
    fun setup() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidLogger()
                androidContext(application)
                applyModules()
            }
        }
    }

    @Test
    fun testWeatherStateUpdateDisplaysNewStateOnSuccess() {
        // Arrange
        coEvery { weatherRepository.fetchWeather(dummyWeatherDetail1.area) } returns dummyWeatherDetail1
        coEvery { weatherRepository.fetchWeather(dummyWeatherDetail2.area) } returns dummyWeatherDetail2

        composeRule.setContent {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
            )
        }

        // Verify the initial state
        composeRule.onNodeWithTag("area_name").assertIsNotDisplayed()
        composeRule.onNodeWithTag("min_temp").assertIsNotDisplayed()
        composeRule.onNodeWithTag("max_temp").assertIsNotDisplayed()

        // Trigger the reload
        composeRule.onNodeWithTag("reload_button").performClick()

        // Verify the updated state
        composeRule.onNodeWithTag("area_name").assertTextEquals(dummyWeatherDetail1.areaName)
        composeRule.onNodeWithTag("min_temp").assertTextEquals("%.2f℃".format(dummyWeatherDetail1.minTemp))
        composeRule.onNodeWithTag("max_temp").assertTextEquals("%.2f℃".format(dummyWeatherDetail1.maxTemp))

        // Trigger the next
        composeRule.onNodeWithTag("next_button").performClick()

        // Verify the updated state
        composeRule.onNodeWithTag("area_name").assertTextEquals(dummyWeatherDetail2.areaName)
        composeRule.onNodeWithTag("min_temp").assertTextEquals("%.2f℃".format(dummyWeatherDetail2.minTemp))
        composeRule.onNodeWithTag("max_temp").assertTextEquals("%.2f℃".format(dummyWeatherDetail2.maxTemp))
    }

    @Test
    fun testWeatherStateUpdateShowsErrorDialogOnFailure() {
        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val errorTitle = context.getString(R.string.error_title_common)
        val errorMessage = context.getString(R.string.error_message_common)

        coEvery { weatherRepository.fetchWeather(dummyWeatherDetail1.area) } throws Exception()

        composeRule.setContent {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
            )
        }

        // Verify the initial state
        composeRule.onNodeWithTag("area_name").assertIsNotDisplayed()
        composeRule.onNodeWithTag("min_temp").assertIsNotDisplayed()
        composeRule.onNodeWithTag("max_temp").assertIsNotDisplayed()

        // Trigger the reload
        composeRule.onNodeWithTag("reload_button").performClick()

        // Verify the error dialog is displayed
        composeRule.onNodeWithText(errorTitle).assertIsDisplayed()
        composeRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }
}
