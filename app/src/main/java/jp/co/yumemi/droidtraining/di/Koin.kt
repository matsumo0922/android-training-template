package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.datasource.di.ApiModule
import org.koin.core.KoinApplication
import org.koin.ksp.generated.module

fun KoinApplication.applyModules() {
    modules(AppModule().module)
    modules(ApiModule().module)
}
