package jp.co.yumemi.droidtraining.core.model

enum class Area(val id: Long) {
    SAPPORO(2128295),
    KUSHIRO(2129376),
    SENDAI(2111149),
    NIIGATA(1855431),
    TOKYO(1850144),
    NAGOYA(1856057),
    KANAZAWA(1860243),
    OSAKA(1853909),
    HIROSHIMA(1862415),
    KOCHI(1859146),
    FUKUOKA(1863967),
    KAGOSHIMA(1860827),
    NAHA(1856035),
    NEW_YORK(5128581),
    LONDON(2643743),
    UNKNOWN(-1),
    ;

    companion object {
        fun fromId(id: Long): Area {
            return entries.find { it.id == id } ?: UNKNOWN
        }

        fun next(currentArea: Area): Area {
            val areas = entries.toMutableList().apply { remove(UNKNOWN) }
            val nextIndex = (currentArea.ordinal + 1) % areas.size
            return areas[nextIndex]
        }
    }
}
