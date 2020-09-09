package com.smtm.mvvm.di


import com.smtm.mvvm.presentation.bookmark.BookmarkViewModel
import com.smtm.mvvm.presentation.main.MainViewModel
import com.smtm.mvvm.presentation.search.SearchViewModel
import com.smtm.mvvm.presentation.webview.WebViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 */
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { BookmarkViewModel(get(), get()) }

    viewModel { WebViewModel(get(), get()) }
}