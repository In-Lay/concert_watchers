package com.inlay.concertswatcher

import android.app.Application
import com.inlay.concertswatcher.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SampleApp)
            modules(mainModule)
        }
    }
}