package jp.co.yumemi.droidtraining.core.model

data class YumemiConfig(
    val openWeatherMapApiKey: String,
) {
    companion object {
        val DEFAULT = YumemiConfig(
            openWeatherMapApiKey = ""
        )
    }
}
