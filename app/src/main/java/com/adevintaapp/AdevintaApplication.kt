package com.adevintaapp

import android.app.Application
import android.content.SharedPreferences
import com.adevintaapp.framework.di.beerModule
import com.adevintaapp.framework.di.dataModule
import com.adevintaapp.framework.local.PreferenceHelper.initPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AdevintaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        initSharedPrefs()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@AdevintaApplication)
            modules(
                listOf(
                    dataModule,
                    beerModule
                )
            )
        }
    }

    private fun initSharedPrefs() {
        prefs = initPrefs(this)
    }

    companion object {
        lateinit var prefs: SharedPreferences
            private set
    }
}