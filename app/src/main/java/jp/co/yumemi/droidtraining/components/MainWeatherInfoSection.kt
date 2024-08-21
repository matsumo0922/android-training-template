package jp.co.yumemi.droidtraining.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherResponse
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.bold
import jp.co.yumemi.droidtraining.core.ui.center
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainWeatherInfoSection(
    weather: WeatherResponse,
    modifier: Modifier = Modifier,
) {
    val weatherIcon = when (weather.weather) {
        Weather.Sunny -> R.drawable.vec_sunny
        Weather.Cloudy -> R.drawable.vec_cloudy
        Weather.Rainy -> R.drawable.vec_rainy
        Weather.Snowy -> R.drawable.vec_snowy
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = weather.weather.name,
            style = MaterialTheme.typography.titleMedium.bold().center(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(weatherIcon),
            contentDescription = "Weather Icon",
        )

        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "${weather.minTemp}℃",
                style = MaterialTheme.typography.bodyMedium.center(),
                color = Color.Blue,
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "${weather.maxTemp}℃",
                style = MaterialTheme.typography.bodyMedium.center(),
                color = Color.Red,
            )
        }
    }
}

@ComponentPreviews
@Composable
private fun MainWeatherInfoSectionPreviewSunny() {
    YumemiTheme {
        MainWeatherInfoSection(
            weather = Weather.Sunny,
        )
    }
}

@ComponentPreviews
@Composable
private fun MainWeatherInfoSectionPreviewCloudy() {
    YumemiTheme {
        MainWeatherInfoSection(
            weather = Weather.Cloudy,
        )
    }
}

@ComponentPreviews
@Composable
private fun MainWeatherInfoSectionPreviewRainy() {
    YumemiTheme {
        MainWeatherInfoSection(
            weather = Weather.Rainy,
        )
    }
}

@ComponentPreviews
@Composable
private fun MainWeatherInfoSectionPreviewSnowy() {
    YumemiTheme {
        MainWeatherInfoSection(
            weather = Weather.Snowy,
        )
    }
}

@ComponentPreviews
@Composable
private fun MainWeatherInfoSectionPreviewUnknown() {
    YumemiTheme {
        MainWeatherInfoSection(
            weather = Weather.Unknown,
        )
    }
}
