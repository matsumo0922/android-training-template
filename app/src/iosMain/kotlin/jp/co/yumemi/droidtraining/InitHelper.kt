package jp.co.yumemi.droidtraining

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import jp.co.yumemi.droidtraining.di.applyModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        applyModules()
    }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}
