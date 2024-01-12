package com.example.signaldetector.application

import android.app.Application
import com.example.signaldetector.model.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class SignalDetectorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@SignalDetectorApp)
            modules(appModule)
        }
    }
}