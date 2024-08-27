package jp.co.yumemi.droidtraining.feature.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import jp.co.yumemi.droidtraining.core.ui.CONTAINER_MAX_WIDTH
import jp.co.yumemi.droidtraining.feature.detail.DetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailIdleContent(
    uiState: DetailUiState,
    onClickBack: () -> Unit,
    onClickWeather: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                areaName = uiState.weatherForecast.areaName,
                scrollBehavior = scrollBehavior,
                onClickBack = onClickBack,
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = CONTAINER_MAX_WIDTH)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    top = 16.dp + padding.calculateTopPadding(),
                    bottom = 16.dp + padding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp,
                ),
            ) {
                items(
                    items = uiState.weatherForecast.dayWeathers,
                    key = { it.date.toString() },
                ) {
                    DetailWeatherItem(
                        modifier = Modifier.fillMaxWidth(),
                        dayWeather = it,
                        onClickWeather = onClickWeather,
                    )
                }
            }
        }
    }
}
