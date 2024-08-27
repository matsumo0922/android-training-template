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
import io.github.aakira.napier.Napier
import jp.co.yumemi.droidtraining.core.model.WeatherForecast
import jp.co.yumemi.droidtraining.core.ui.colors.tempMaxColor
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun DetailWeatherItem(
    dayWeather: WeatherForecast.DayWeather,
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
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(3f),
                text = dayWeather.date.formatDate(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            WeatherIcon(
                modifier = Modifier.weight(2f),
                dayWeather = dayWeather,
            )

            TempItem(
                modifier = Modifier.weight(5f),
                minTemp = dayWeather.minTemp.coerceIn(MIN_TEMP, MAX_TEMP),
                maxTemp = dayWeather.maxTemp.coerceIn(MIN_TEMP, MAX_TEMP),
            )
        }
    }
}

@Composable
private fun WeatherIcon(
    dayWeather: WeatherForecast.DayWeather,
    modifier: Modifier = Modifier,
) {
    val precipitation = dayWeather.rain ?: dayWeather.snow

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (precipitation != null && precipitation >= 1) {
            Text(
                text = "${precipitation.toInt()}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        AsyncImage(
            modifier = Modifier.size(60.dp),
            model = dayWeather.iconUrl,
            contentDescription = dayWeather.description,
            onError = {
                Napier.d { it.result.throwable.toString() }
            },
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
                valueRange = MIN_TEMP..MAX_TEMP,
                enabled = false,
                startThumb = { },
                endThumb = { },
                colors = SliderDefaults.colors(
                    disabledActiveTrackColor = MaterialTheme.colorScheme.primary,
                    disabledInactiveTrackColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        }

        Text(
            text = "${maxTemp.toInt()}℃",
            style = MaterialTheme.typography.bodyMedium,
            color = tempMaxColor,
        )
    }
}

fun Instant.formatDate(): String {
    val localDateTime = this.toLocalDateTime(TimeZone.UTC)

    val month = localDateTime.monthNumber.toString().padStart(2, '0')
    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')

    return "$month/$day: $hour:$minute"
}

private const val MAX_TEMP = 35f
private const val MIN_TEMP = 15f
