package jp.co.yumemi.droidtraining.feature.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
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
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
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
