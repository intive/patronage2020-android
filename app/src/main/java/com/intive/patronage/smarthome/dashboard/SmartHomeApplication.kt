package com.intive.patronage.smarthome.dashboard

import android.app.Application
import com.intive.patronage.smarthome.dashboard.di.lightsDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SmartHomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SmartHomeApplication)
            modules(lightsDetailsModule)
        }
    }
}