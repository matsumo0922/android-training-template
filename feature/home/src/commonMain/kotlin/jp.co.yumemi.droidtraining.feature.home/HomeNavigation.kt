package jp.co.yumemi.droidtraining.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.co.yumemi.droidtraining.core.model.Area

const val HomeRoute = "home"

fun NavController.navigateToHome() {
    this.navigate(HomeRoute)
}

fun NavGraphBuilder.homeScreen(
    navigateToDetail: (Area) -> Unit,
) {
    composable(HomeRoute) {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            onClickNext = navigateToDetail,
        )
    }
}
