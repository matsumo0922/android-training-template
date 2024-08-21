package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.droidtraining.BuildConfig
import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Named("AppModule")
@ComponentScan("jp.co.yumemi.droidtraining")
class AppModule {

    @Single
    @Named("ioDispatcher")
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Single
    @Named("mainDispatcher")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Single
    fun provideYumemiConfig() = YumemiConfig(
        openWeatherMapApiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY,
    )
}
