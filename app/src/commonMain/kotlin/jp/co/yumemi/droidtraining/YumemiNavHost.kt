package jp.co.yumemi.droidtraining

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import jp.co.yumemi.droidtraining.core.ui.animation.NavigateAnimation
import jp.co.yumemi.droidtraining.feature.detail.detailScreen
import jp.co.yumemi.droidtraining.feature.detail.navigateToDetail
import jp.co.yumemi.droidtraining.feature.home.HomeRoute
import jp.co.yumemi.droidtraining.feature.home.homeScreen

@Composable
fun YumemiNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { NavigateAnimation.Horizontal.enter },
        exitTransition = { NavigateAnimation.Horizontal.exit },
        popEnterTransition = { NavigateAnimation.Horizontal.popEnter },
        popExitTransition = { NavigateAnimation.Horizontal.popExit },
    ) {
        homeScreen(
            navigateToDetail = navController::navigateToDetail,
        )

        detailScreen(
            terminate = navController::popBackStack,
        )
    }
}
