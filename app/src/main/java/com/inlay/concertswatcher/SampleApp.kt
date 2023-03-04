package com.inlay.concertswatcher

import android.app.Application
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.inlay.concertswatcher.di.favouriteScreen
import com.inlay.concertswatcher.di.mainModule
import com.inlay.concertswatcher.di.searchScreen
import com.inlay.details.di.detailsModule
import com.inlay.login.di.loginModule
import com.inlay.map.di.mapsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.auth
        Firebase.storage
        startKoin {
            androidLogger()
            androidContext(this@SampleApp)
            modules(
                mainModule,
                detailsModule,
                mapsModule,
                favouriteScreen,
                searchScreen,
                loginModule
            )
        }
    }
}