package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val DetailRoute = "detail"

fun NavController.navigateToDetail() {
    this.navigate(DetailRoute)
}

fun NavGraphBuilder.detailScreen() {
    composable(DetailRoute) {
        DetailScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
