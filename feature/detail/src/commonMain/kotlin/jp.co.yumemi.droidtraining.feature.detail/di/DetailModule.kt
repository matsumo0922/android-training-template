package jp.co.yumemi.droidtraining.feature.detail.di

import jp.co.yumemi.droidtraining.core.model.Area
import jp.co.yumemi.droidtraining.core.repository.YumemiWeatherRepository
import jp.co.yumemi.droidtraining.feature.detail.DetailViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Named("DataSourceModule")
@ComponentScan("jp.co.yumemi.droidtraining.feature.detail")
class DetailModule {

    @Factory
    fun provideDetailViewModel(
        @InjectedParam area: Area,
        weatherRepository: YumemiWeatherRepository,
    ) = DetailViewModel(
        area = area,
        weatherRepository = weatherRepository,
    )
}
