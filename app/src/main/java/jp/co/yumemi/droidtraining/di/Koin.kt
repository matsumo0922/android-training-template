package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.api.di.apiModule
import org.koin.core.KoinApplication

fun KoinApplication.applyModules() {
    modules(appModule)
    modules(apiModule)
}
