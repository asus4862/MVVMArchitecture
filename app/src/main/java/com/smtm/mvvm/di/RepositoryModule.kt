package com.smtm.mvvm.di


import com.smtm.mvvm.data.repository.user.UserRepository
import com.smtm.mvvm.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module.module

/**
 **/
val repositoryModule = module {

    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }
}