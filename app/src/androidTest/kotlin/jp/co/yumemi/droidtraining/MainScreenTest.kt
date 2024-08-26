package jp.co.yumemi.droidtraining

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.mockk
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.repository.YumemiWeatherRepository
import jp.co.yumemi.droidtraining.di.applyModules
import jp.co.yumemi.droidtraining.feature.home.HomeViewModel
import kotlinx.datetime.Instant
import org.junit.Before
import org.junit.Rule
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
}
