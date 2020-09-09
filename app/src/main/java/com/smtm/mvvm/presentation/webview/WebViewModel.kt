package com.smtm.mvvm.presentation.webview

import android.app.Application
import android.content.res.AssetManager
import android.os.Environment
import android.text.format.DateFormat
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smtm.mvvm.R
import com.smtm.mvvm.data.remote.request.ConnectRequest
import com.smtm.mvvm.data.remote.request.Template
import com.smtm.mvvm.data.remote.request.TokenRequest
import com.smtm.mvvm.data.remote.request.UserData
import com.smtm.mvvm.data.remote.response.ConnectResponse
import com.smtm.mvvm.data.remote.response.TokenResponse
import com.smtm.mvvm.data.repository.error.RepositoryException
import com.smtm.mvvm.data.repository.user.UserRepository
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.presentation.base.BaseViewModel
import com.smtm.mvvm.presentation.search.SearchState
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.*


/**
 */
class WebViewModel(
    application: Application,
    private val userRepository: UserRepository
) : BaseViewModel(application) {

    private val _userDocuments = MutableLiveData<TokenResponse>()
    val userDocuments: LiveData<TokenResponse> = _userDocuments

    private val _apiState = MutableLiveData<SearchState>(SearchState.NONE)
    val searchState: LiveData<SearchState> = _apiState

    private var query: String = ""

    init {
//        requestImagesToRepository()
    }

    private var mUserDocument: UserDocument? = null

//    private fun observeChangingFavoriteImage() {
//        userRepository.observeChangingFavoriteImage()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ changedUserDocument ->
//                _userDocuments.replace(changedUserDocument) {
//                    it.id == changedUserDocument.id }
//            }, { throwable ->
//                Log.d("gg", throwable.message)
//            })
//            .disposeByOnCleared()
//    }


    fun doSearch() {
        Log.e("gg", "doSearch()")
        requestToken()
    }
    fun jsSave(js: String) {
        Log.e("gg", "jsSave()")
        query = js
    }
    private fun requestToken() {
        _apiState.value = SearchState.NONE
        val request = TokenRequest("pca-test-8", "abef01f177c1")
        userRepository.getToken(request)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _apiState.value = SearchState.SUCCESS }
            .doOnError { _apiState.value = SearchState.FAIL }
            .subscribe({ response ->
                requestConnection(response)
            }, { throwable ->
                handlingImageSearchError(throwable)
            })
            .disposeByOnCleared()
    }

    private fun requestConnection(tokenInfo: TokenResponse) {
        _apiState.value = SearchState.NONE

//        var data: ArrayList<String> = ArrayList()
//        data.add("http://www.gravatar.com/avatar/68f912364117ca0d704a9d20f343b2b2?d=identicon&s=40")

        var userData: UserData = UserData("student", "teacher", query)
        var template: Template = Template("mobile-student")
        val request = ConnectRequest("abe16", "class-room-090", userData, template, "delta-canvas-demo")
        userRepository.getConnect("Bearer " + tokenInfo.token, request)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _apiState.value = SearchState.SUCCESS }
            .doOnError { _apiState.value = SearchState.FAIL }
            .subscribe({ response ->
                updateDocuments(response)
            }, { throwable ->
                handlingImageSearchError(throwable)
            })
            .disposeByOnCleared()
    }

    private fun updateDocuments(
        receivedImageDocuments: ConnectResponse
    ) {
        _userDocuments.apply {
            saveHtmlFile(receivedImageDocuments.html)
        }.also {
            //            if (it.isEmpty()) {
//                updateShowMessage(R.string.success_image_search_no_result)
//            }

        }
    }




    private fun saveHtmlFile(html: String) {
        val path: String = Environment.getExternalStorageDirectory().getPath()
        var fileName: String =
            DateFormat.format("dd_MM_yyyy_hh_mm_ss", System.currentTimeMillis()).toString()
        fileName = "fileName15.html"
        val file = File(path, fileName)
//        val html = "<html><head><title>Title</title></head><body>This is random text.</body></html>"
        try {
            val out = FileOutputStream(file)
            val data = html.toByteArray()
            out.write(data)
            out.close()
            Log.e("gg", "File Save : " + file.getPath())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun handlingImageSearchError(throwable: Throwable) {
        when (throwable) {
            is RepositoryException.NetworkNotConnectingException -> {
                updateShowMessage(R.string.network_not_connecting_error)
            }
            else -> {
                updateShowMessage(R.string.unknown_error)
            }
        }
    }
}
