package jp.co.yumemi.droidtraining.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.co.yumemi.droidtraining.core.ui.R
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainActionButtonsSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MainActionButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.main_weather_action_reload),
            onClick = {},
        )

        MainActionButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.main_weather_action_reload),
            onClick = {},
        )
    }
}

@Composable
private fun MainActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
        ),
    ) {
        Text(
            text = text,
        )
    }
}

@ComponentPreviews
@Composable
private fun MainActionButtonsSectionPreview() {
    YumemiTheme {
        MainActionButtonsSection()
    }
}
