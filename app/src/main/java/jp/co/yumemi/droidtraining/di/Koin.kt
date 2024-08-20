package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.datasource.di.DataSourceModule
import org.koin.core.KoinApplication
import org.koin.ksp.generated.module

fun KoinApplication.applyModules() {
    modules(AppModule().module)
    modules(DataSourceModule().module)
}
