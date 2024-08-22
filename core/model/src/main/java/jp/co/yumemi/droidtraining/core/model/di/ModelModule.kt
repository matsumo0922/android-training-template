package jp.co.yumemi.droidtraining.core.model.di

import jp.co.yumemi.droidtraining.core.model.YumemiConfig
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Named("ModelModule")
@ComponentScan("jp.co.yumemi.droidtraining.core.model")
class ModelModule {

    @Single
    fun provideTestConfig() = YumemiConfig(
        openWeatherMapApiKey = ""
    )
}
