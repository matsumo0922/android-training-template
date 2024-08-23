package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.co.yumemi.droidtraining.core.model.Area

const val DetailAreaId = "areaId"
const val DetailRoute = "detail/{$DetailAreaId}"

fun NavController.navigateToDetail(area: Area) {
    this.navigate("detail/${area.id}")
}

fun NavGraphBuilder.detailScreen(
    terminate: () -> Unit,
) {
    composable(
        route = DetailRoute,
        arguments = listOf(
            navArgument(DetailAreaId) { type = NavType.LongType },
        ),
    ) {
        DetailScreen(
            modifier = Modifier.fillMaxSize(),
            area = Area.fromId(it.arguments?.getLong(DetailAreaId) ?: 0),
            terminate = terminate,
        )
    }
}
