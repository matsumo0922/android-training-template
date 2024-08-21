package jp.co.yumemi.droidtraining.core.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.co.yumemi.droidtraining.core.model.Weather
import jp.co.yumemi.droidtraining.core.model.WeatherDetail
import kotlinx.datetime.Instant

class WeatherResponsePreviewParameter : PreviewParameterProvider<WeatherDetail> {

    override val values: Sequence<WeatherDetail>
        get() = sequenceOf(
            dummy,
            dummy.copy(
                weather = Weather.Cloudy,
                area = "大阪",
            ),
            dummy.copy(
                weather = Weather.Rainy,
                area = "名古屋",
            ),
            dummy.copy(
                weather = Weather.Snowy,
                area = "札幌",
            ),
        )

    companion object {
        val dummy = WeatherDetail(
            weather = Weather.Sunny,
            maxTemp = 30,
            minTemp = 20,
            date = Instant.parse("2022-01-01T00:00:00Z"),
            area = "東京",
        )
    }
}
