package jp.co.yumemi.droidtraining.feature.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.ui.components.LoadingScreen
import jp.co.yumemi.droidtraining.feature.detail.components.DetailIdleContent
import jp.co.yumemi.droidtraining.feature.detail.components.DetailTopAppBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Suppress("UnusedParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(
    area: Area,
    terminate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = koinViewModel {
        parametersOf(area)
    },
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                areaName = "名古屋",
                scrollBehavior = scrollBehavior,
                onClickBack = terminate,
            )
        },
    ) { padding ->
        AnimatedContent(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            targetState = screenState,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) },
            contentKey = { it::class.simpleName },
            label = "DetailScreen",
        ) { state ->
            when (state) {
                is DetailScreenState.Idle -> {
                    DetailIdleContent(
                        modifier = Modifier.fillMaxSize(),
                        scrollBehavior = scrollBehavior,
                        onClickWeather = { /* not implemented */ },
                    )
                }

                is DetailScreenState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                }

                is DetailScreenState.Error -> {
                    // TODO: 2024/08/23 daichi-matsumoto implement error dialog
                }
            }
        }
    }
}
