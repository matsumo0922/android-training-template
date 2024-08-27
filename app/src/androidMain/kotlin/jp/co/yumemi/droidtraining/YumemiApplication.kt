package jp.co.yumemi.droidtraining

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import jp.co.yumemi.droidtraining.di.applyModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YumemiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@YumemiApplication)
            androidLogger()
            applyModules()
        }

        Napier.base(DebugAntilog())
    }
}
