package jp.co.yumemi.droidtraining.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.droidtraining.core.ui.Res
import jp.co.yumemi.droidtraining.core.ui.main_weather_action_next
import jp.co.yumemi.droidtraining.core.ui.main_weather_action_reload
import jp.co.yumemi.droidtraining.feature.home.R
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MainActionButtonsSection(
    onClickReload: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MainActionButton(
            modifier = Modifier
                .testTag("reload_button")
                .weight(1f),
            text = stringResource(Res.string.main_weather_action_reload),
            onClick = onClickReload,
        )

        MainActionButton(
            modifier = Modifier
                .testTag("next_button")
                .weight(1f),
            text = stringResource(Res.string.main_weather_action_next),
            onClick = onClickNext,
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
        contentPadding = PaddingValues(),
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 18.sp,
        )
    }
}
