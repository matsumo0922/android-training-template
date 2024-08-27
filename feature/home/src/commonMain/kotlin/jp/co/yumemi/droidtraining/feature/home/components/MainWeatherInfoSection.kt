package jp.co.yumemi.droidtraining.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import jp.co.yumemi.droidtraining.core.ui.Res
import jp.co.yumemi.droidtraining.core.ui.bold
import jp.co.yumemi.droidtraining.core.ui.center
import jp.co.yumemi.droidtraining.core.ui.vec_cloudy
import jp.co.yumemi.droidtraining.core.ui.vec_rainy
import jp.co.yumemi.droidtraining.core.ui.vec_snowy
import jp.co.yumemi.droidtraining.core.ui.vec_sunny
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun MainWeatherInfoSection(
    weather: WeatherDetail,
    modifier: Modifier = Modifier,
) {
    val weatherIcon = when (weather.weather) {
        Weather.Sunny -> Res.drawable.vec_sunny
        Weather.Cloudy -> Res.drawable.vec_cloudy
        Weather.Rainy -> Res.drawable.vec_rainy
        Weather.Snowy -> Res.drawable.vec_snowy
        else -> null
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .testTag("area_name")
                .fillMaxWidth(),
            text = weather.areaName,
            style = MaterialTheme.typography.titleMedium.bold().center(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        if (weatherIcon != null) {
            Image(
                modifier = Modifier
                    .testTag("weather_icon")
                    .weight(1f, false)
                    .aspectRatio(1f),
                painter = painterResource(weatherIcon),
                contentDescription = "Weather Icon",
            )
        }

        Row {
            Text(
                modifier = Modifier
                    .testTag("min_temp")
                    .weight(1f),
                text = "${weather.minTemp}℃",
                style = MaterialTheme.typography.bodyMedium.center(),
                color = Color.Blue,
            )

            Text(
                modifier = Modifier
                    .testTag("max_temp")
                    .weight(1f),
                text = "${weather.maxTemp}℃",
                style = MaterialTheme.typography.bodyMedium.center(),
                color = Color.Red,
            )
        }
    }
}
