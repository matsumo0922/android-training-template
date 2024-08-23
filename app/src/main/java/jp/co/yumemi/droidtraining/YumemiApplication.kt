package jp.co.yumemi.droidtraining

import android.app.Application
import jp.co.yumemi.droidtraining.core.common.YumemiDebugTree
import jp.co.yumemi.droidtraining.di.applyModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class YumemiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(YumemiDebugTree())

        startKoin {
            androidContext(this@YumemiApplication)
            androidLogger()
            applyModules()
        }
    }
}
