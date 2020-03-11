package com.intive.patronage.smarthome

import android.app.Application
import com.intive.patronage.smarthome.spashscreen.splashScreenModule
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
            modules(listOf(splashScreenModule))
        }
    }
}