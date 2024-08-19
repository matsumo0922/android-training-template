package jp.co.yumemi.droidtraining.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Named("AppModule")
@ComponentScan("jp.co.yumemi.droidtraining")
class AppModule
