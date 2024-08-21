package jp.co.yumemi.droidtraining.core.model

enum class Area(val id: Int) {
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
        fun fromId(id: Int): Area {
            return entries.find { it.id == id } ?: UNKNOWN
        }
    }
}
