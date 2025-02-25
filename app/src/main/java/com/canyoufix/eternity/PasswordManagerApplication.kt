package com.canyoufix.eternity

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

import com.canyoufix.ui.di.UiModule

class PasswordManagerApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        GlobalContext.startKoin {
            androidContext(this@PasswordManagerApplication)
            modules(listOf(UiModule))
        }
    }
}