package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.repository.di.ApiModule
import org.koin.core.KoinApplication
import org.koin.ksp.generated.module

fun KoinApplication.applyModules() {
    modules(AppModule().module)
    modules(ApiModule().module)
}
