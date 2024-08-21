package jp.co.yumemi.droidtraining.core.datasource

import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class YumemiWeatherSource(
    private val formatter: Json,
) {
    
}
