package jp.co.yumemi.droidtraining.core.datasource.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Named("DataSourceModule")
@ComponentScan("jp.co.yumemi.droidtraining.core.datasource")
class DataSourceModule
