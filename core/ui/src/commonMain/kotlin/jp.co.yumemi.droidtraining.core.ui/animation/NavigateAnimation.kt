package jp.co.yumemi.droidtraining.core.ui.animation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset

object NavigateAnimation {
    val decelerateEasing = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f)

    object Horizontal {
        private const val DURATION_FADE = 300
        private const val DURATION_SLIDE = 300

        val enter = fadeIn(tween(DURATION_FADE)) + slideIn(
            animationSpec = tween(DURATION_SLIDE, 0, decelerateEasing),
            initialOffset = { IntOffset((it.width * 0.1).toInt(), 0) },
        )

        val popExit = fadeOut(tween(DURATION_FADE)) + slideOut(
            animationSpec = tween(DURATION_SLIDE, 0, decelerateEasing),
            targetOffset = { IntOffset((it.width * 0.1).toInt(), 0) },
        )

        val popEnter = fadeIn(tween(DURATION_FADE)) + slideIn(
            animationSpec = tween(DURATION_SLIDE, 0, decelerateEasing),
            initialOffset = { IntOffset((-it.width * 0.1).toInt(), 0) },
        )

        val exit = fadeOut(tween(DURATION_FADE)) + slideOut(
            animationSpec = tween(DURATION_SLIDE, 0, decelerateEasing),
            targetOffset = { IntOffset((-it.width * 0.1).toInt(), 0) },
        )
    }
}
