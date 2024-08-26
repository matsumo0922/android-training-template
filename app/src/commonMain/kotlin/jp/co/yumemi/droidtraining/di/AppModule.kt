package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Named("AppModule")
@ComponentScan("jp.co.yumemi.droidtraining")
class AppModule {

    @Single
    fun provideYumemiConfig() = YumemiConfig(
        openWeatherMapApiKey = "",
    )
}
