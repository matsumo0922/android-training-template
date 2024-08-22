package jp.co.yumemi.droidtraining.core.datasource.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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

    @Single
    fun provideHttpClient() = HttpClient {
        install(ContentNegotiation) {
            json(provideJsonFormatter())
        }

        install(HttpRequestRetry) {
            maxRetries = 3
            exponentialDelay()
        }
    }
}
