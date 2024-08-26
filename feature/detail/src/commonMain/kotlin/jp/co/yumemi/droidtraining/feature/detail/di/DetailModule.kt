package jp.co.yumemi.droidtraining.feature.detail.di

import jp.co.yumemi.droidtraining.feature.detail.DetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailModule = module {
    viewModelOf(::DetailViewModel)
}
