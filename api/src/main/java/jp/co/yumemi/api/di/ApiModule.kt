package jp.co.yumemi.api.di

import jp.co.yumemi.api.YumemiWeather
import org.koin.dsl.module

val apiModule = module {
    single {
        YumemiWeather()
    }
}
