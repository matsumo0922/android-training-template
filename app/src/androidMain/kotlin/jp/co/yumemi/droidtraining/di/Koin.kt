package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.droidtraining.core.datasource.di.DataSourceModule
import jp.co.yumemi.droidtraining.core.repository.di.RepositoryModule
import jp.co.yumemi.droidtraining.feature.detail.di.DetailModule
import jp.co.yumemi.droidtraining.feature.home.di.HomeModule
import org.koin.core.KoinApplication
import org.koin.ksp.generated.module

fun KoinApplication.applyModules() {
    // App
    modules(AppModule().module)

    // Core
    modules(DataSourceModule().module)
    modules(RepositoryModule().module)

    // Feature
    modules(HomeModule().module)
    modules(DetailModule().module)
}
