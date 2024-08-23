package jp.co.yumemi.droidtraining.feature.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.colors.tempMaxColor
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun DetailWeatherItem(
    onClickWeather: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickWeather.invoke() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(3f),
                text = "8月28日(水)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            WeatherIcon(
                modifier = Modifier.weight(2f)
            )

            TempItem(
                modifier = Modifier.weight(5f),
                minTemp = 24f,
                maxTemp = 31f,
            )
        }
    }
}

@Composable
private fun WeatherIcon(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "62%",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        AsyncImage(
            modifier = Modifier.size(60.dp),
            model = "https://openweathermap.org/img/wn/10d@4x.png",
            contentDescription = "晴れのち雨",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TempItem(
    minTemp: Float,
    maxTemp: Float,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "${minTemp.toInt()}℃",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            RangeSlider(
                modifier = Modifier.weight(1f),
                value = minTemp..maxTemp,
                onValueChange = { /* do nothing */ },
                valueRange = 20f..40f,
                enabled = false,
                startThumb = { },
                endThumb = { },
                colors = SliderDefaults.colors(
                    disabledActiveTrackColor = MaterialTheme.colorScheme.primary,
                    disabledInactiveTrackColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }

        Text(
            text = "${maxTemp.toInt()}℃",
            style = MaterialTheme.typography.bodyMedium,
            color = tempMaxColor,
        )
    }
}

@ComponentPreviews
@Composable
private fun DetailWeatherItemPreview() {
    YumemiTheme {
        DetailWeatherItem(
            modifier = Modifier.fillMaxWidth(),
            onClickWeather = {},
        )
    }
}
