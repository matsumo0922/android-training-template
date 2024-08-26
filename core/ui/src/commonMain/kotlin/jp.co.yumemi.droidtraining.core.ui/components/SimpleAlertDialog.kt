package jp.co.yumemi.droidtraining.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.bold
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Suppress("ModifierMissing")
@Composable
fun SimpleAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {},
    isCaution: Boolean = false,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.titleMedium.bold(),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (negativeButtonText != null) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(4.dp),
                            onClick = {
                                onNegativeButtonClick.invoke()
                            },
                        ) {
                            Text(text = negativeButtonText)
                        }
                    }

                    if (positiveButtonText != null) {
                        Button(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(if (isCaution) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary),
                            onClick = {
                                onPositiveButtonClick.invoke()
                            },
                        ) {
                            Text(text = positiveButtonText)
                        }
                    }
                }
            }
        }
    }
}

@ComponentPreviews
@Composable
private fun SimpleAlertDialogPreview() {
    YumemiTheme {
        SimpleAlertDialog(
            title = "Title",
            message = "Message",
            positiveButtonText = "Positive",
            negativeButtonText = "Negative",
            onDismissRequest = {},
        )
    }
}

@ComponentPreviews
@Composable
private fun SimpleAlertDialogPreviewCaution() {
    YumemiTheme {
        SimpleAlertDialog(
            title = "Title",
            message = "Message",
            positiveButtonText = "Positive",
            negativeButtonText = "Negative",
            isCaution = true,
            onDismissRequest = {},
        )
    }
}
