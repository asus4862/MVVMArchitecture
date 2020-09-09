package com.smtm.mvvm.di

import com.smtm.mvvm.data.remote.api.SearchAPI
import com.smtm.mvvm.data.remote.api.UserRemoteDataSourceImpl
import com.smtm.mvvm.data.repository.user.UserRemoteDataSource
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 */
val apiModule = module {

    single<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(get())
    }

    single(createOnStart = false) { get<Retrofit>().create(SearchAPI::class.java) }
}