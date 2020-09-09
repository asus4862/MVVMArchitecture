package com.smtm.mvvm.di

import com.smtm.mvvm.data.db.AppDatabase
import com.smtm.mvvm.data.db.UserLocalDataSourceImpl
import com.smtm.mvvm.data.repository.user.UserLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 */
val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }

    single<UserLocalDataSource> {
        UserLocalDataSourceImpl(get())
    }

    single {
        get<AppDatabase>().getBookmarkDao()
    }
}