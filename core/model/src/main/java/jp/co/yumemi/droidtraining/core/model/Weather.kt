package jp.co.yumemi.droidtraining.core.model

enum class Weather {
    Sunny, Cloudy, Rainy, Snowy, Unknown;

    companion object {
        fun fromString(value: String): Weather {
            return when (value.lowercase()) {
                "sunny" -> Sunny
                "cloudy" -> Cloudy
                "rainy" -> Rainy
                "snow" -> Snowy
                else -> Unknown
            }
        }
    }
}
