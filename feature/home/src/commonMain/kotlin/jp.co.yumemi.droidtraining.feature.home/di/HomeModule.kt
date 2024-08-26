package jp.co.yumemi.droidtraining.feature.home.di

import jp.co.yumemi.droidtraining.feature.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}
