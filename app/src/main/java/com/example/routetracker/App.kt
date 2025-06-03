package com.example.routetracker

import android.app.Application
import com.example.routetracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            module {
                androidLogger()
                androidContext(this@App)
                modules(appModule)
            }
        }
    }

}