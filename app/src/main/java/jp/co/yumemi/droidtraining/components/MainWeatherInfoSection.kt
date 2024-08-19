package jp.co.yumemi.droidtraining.components

import androidx.compose.foundation.Image
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
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.center
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainWeatherInfoSection(
    weather: Weather,
    modifier: Modifier = Modifier,
) {
    val weatherIcon = when (weather) {
        Weather.Sunny -> R.drawable.vec_sunny
        Weather.Cloudy -> R.drawable.vec_cloudy
        Weather.Rainy -> R.drawable.vec_rainy
        Weather.Snowy -> R.drawable.vec_snowy
        Weather.Unknown -> null
    }

    Column(modifier) {
        if (weatherIcon != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(weatherIcon),
                contentDescription = "Weather Icon",
            )
        }

        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "10℃",
                style = MaterialTheme.typography.bodyMedium.center(),
                color = Color.Blue,
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "20℃",
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
