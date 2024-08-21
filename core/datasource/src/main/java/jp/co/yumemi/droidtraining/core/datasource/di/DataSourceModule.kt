package jp.co.yumemi.droidtraining.core.datasource.di

import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Named("DataSourceModule")
@ComponentScan("jp.co.yumemi.droidtraining.core.datasource")
class DataSourceModule {

    @Single
    fun provideJsonFormatter() = Json {
        isLenient = true
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
        explicitNulls = false
    }
}
