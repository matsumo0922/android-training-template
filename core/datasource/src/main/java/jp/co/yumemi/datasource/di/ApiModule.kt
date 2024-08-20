package jp.co.yumemi.datasource.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Named("ApiModule")
@ComponentScan("jp.co.yumemi.api")
class ApiModule
