package jp.co.yumemi.droidtraining.core.repository.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Named("RepositoryModule")
@ComponentScan("jp.co.yumemi.droidtraining.core.repository")
class RepositoryModule
