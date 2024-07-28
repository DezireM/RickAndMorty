package com.example.aruuke_hw2_6m

import android.app.Application
import com.example.aruuke_hw2_6m.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}