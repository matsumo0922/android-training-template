package jp.co.yumemi.droidtraining.core.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import kotlinx.datetime.Instant

class WeatherResponsePreviewParameter : PreviewParameterProvider<WeatherDetail> {

    override val values: Sequence<WeatherDetail>
        get() = sequenceOf(
            dummy,
            dummy.copy(
                weather = Weather.Cloudy,
                areaName = "Osaka",
            ),
            dummy.copy(
                weather = Weather.Rainy,
                areaName = "Fukuoka",
            ),
            dummy.copy(
                weather = Weather.Snowy,
                areaName = "Sapporo",
            ),
        )

    companion object {
        val dummy = WeatherDetail(
            weather = Weather.Sunny,
            maxTemp = 30.0,
            minTemp = 20.0,
            date = Instant.parse("2022-01-01T00:00:00Z"),
            area = Area.TOKYO,
            areaName = "Tokyo",
        )
    }
}
