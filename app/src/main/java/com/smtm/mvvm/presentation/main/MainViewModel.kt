package com.smtm.mvvm.presentation.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.smtm.mvvm.presentation.base.BaseViewModel

/**
 */
class MainViewModel(application: Application) : BaseViewModel(application) {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}