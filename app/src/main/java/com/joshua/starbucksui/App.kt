package com.joshua.starbucksui

import android.app.Application
import android.util.Log
import com.joshua.starbucksui.model.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    companion object {
        lateinit var self: Application
        fun applicationContext(): Application {
            return self
        }
    }

    init {
        self = this
    }

    override fun onCreate() {
        super.onCreate()

        val module = listOf(
            dbModule
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(module)
        }

    }
}