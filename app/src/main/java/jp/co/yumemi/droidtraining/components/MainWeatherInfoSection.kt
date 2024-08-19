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
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.center
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainWeatherInfoSection(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(jp.co.yumemi.droidtraining.core.ui.R.drawable.vec_sunny),
            contentDescription = "Weather Icon",
        )

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
private fun MainWeatherInfoSectionPreview() {
    YumemiTheme {
        MainWeatherInfoSection()
    }
}
