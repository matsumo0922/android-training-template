package jp.co.yumemi.droidtraining.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.co.yumemi.droidtraining.core.ui.YumemiTheme
import jp.co.yumemi.droidtraining.core.ui.extensions.ComponentPreviews

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            val (weatherInfoSection, actionButtonsSection) = createRefs()

            MainWeatherInfoSection(
                modifier = Modifier.constrainAs(weatherInfoSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.percent(0.5f)
                    height = Dimension.wrapContent
                },
            )

            MainActionButtonsSection(
                modifier = Modifier.constrainAs(actionButtonsSection) {
                    top.linkTo(weatherInfoSection.bottom, 80.dp)
                    start.linkTo(weatherInfoSection.start)
                    end.linkTo(weatherInfoSection.end)

                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
            )
        }
    }
}

@ComponentPreviews
@Composable
private fun MainScreenPreview() {
    YumemiTheme {
        MainScreen()
    }
}
