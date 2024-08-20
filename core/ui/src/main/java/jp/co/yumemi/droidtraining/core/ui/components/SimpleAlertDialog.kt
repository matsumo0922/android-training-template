package jp.co.yumemi.droidtraining.core.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
fun SimpleAlertDialog(
    title: String? = null,
    message: String? = null,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = title?.letComposable {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = message?.letComposable {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        confirmButton = {
            if (positiveButtonText != null) {
                TextButton(
                    onClick = {
                        onPositiveButtonClick.invoke()
                        onDismissRequest.invoke()
                    }
                ) {
                    Text(positiveButtonText)
                }
            }
        },
        dismissButton = {
            if (negativeButtonText != null) {
                TextButton(
                    onClick = {
                        onNegativeButtonClick.invoke()
                        onDismissRequest.invoke()
                    },
                ) {
                    Text(negativeButtonText)
                }
            }
        },
        onDismissRequest = onDismissRequest,
    )
}

private fun <T> (T).letComposable(f: @Composable (T) -> Unit): (@Composable () -> Unit)? {
    return run {
        {
            f(this)
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
            onPositiveButtonClick = {},
            onNegativeButtonClick = {},
            onDismissRequest = {},
        )
    }
}

