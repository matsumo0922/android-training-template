package jp.co.yumemi.droidtraining.di

import jp.co.yumemi.droidtraining.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}
