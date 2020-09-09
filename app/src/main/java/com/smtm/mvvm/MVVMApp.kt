package com.smtm.mvvm

import android.app.Application
import com.smtm.mvvm.di.*
import org.koin.android.ext.android.startKoin

/**
 */
@Suppress("Unused")
class MVVMApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            networkModule,
            apiModule,
            repositoryModule,
            roomModule,
            viewModelModule,
            helperModule
        ))
    }

}