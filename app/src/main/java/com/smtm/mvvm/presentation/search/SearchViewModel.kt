package com.smtm.mvvm.presentation.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.common.pageload.PageLoadHelper
import com.smtm.mvvm.presentation.base.BaseViewModel
import com.smtm.mvvm.data.remote.request.GithubUserRequest
import com.smtm.mvvm.data.repository.error.RepositoryException
import com.smtm.mvvm.data.repository.user.UserRepository
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.extension.*
import io.reactivex.android.schedulers.AndroidSchedulers


/**
 */
class SearchViewModel(
    application: Application,
    private val userRepository: UserRepository,
    private val pageLoadHelper: PageLoadHelper<String>
) : BaseViewModel(application) {

    private val _userDocuments = MutableLiveData<MutableList<UserDocument>>()
    val userDocuments: LiveData<List<UserDocument>> = Transformations.map(_userDocuments) { it?.toList() }

    private val _imageSearchState = MutableLiveData<SearchState>(SearchState.NONE)
    val searchState: LiveData<SearchState> = _imageSearchState

    private var query: String = ""

    init {
        observeChangingFavoriteImage()
        observePageLoadInspector()
    }

    private var mUserDocument: UserDocument? = null

    private fun observeChangingFavoriteImage() {
        userRepository.observeChangingFavoriteImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ changedUserDocument ->
                _userDocuments.replace(changedUserDocument) {
                    it.id == changedUserDocument.id }
            }, { throwable ->
                Log.d("gg", throwable.message)
            })
            .disposeByOnCleared()
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

    fun doSearch() {
        _userDocuments.clear()
        pageLoadHelper.requestFirstLoad(query)
    }

    fun loadMoreImagesIfPossible(position: Int) {
        pageLoadHelper.requestPreloadIfPossible(position, _userDocuments.size())
    }

    fun retryLoadMoreImageList() {
        pageLoadHelper.requestRetryAsPreviousValue()
    }

    private fun observePageLoadInspector() {
        pageLoadHelper.onPageLoadApproveCallback =
            { key, pageNumber, dataSize, isFirstPage ->
                val request = GithubUserRequest(key, pageNumber, dataSize, isFirstPage)
                requestImagesToRepository(request)
            }
    }

    private fun requestImagesToRepository(request: GithubUserRequest) {
        _imageSearchState.value = SearchState.NONE

        userRepository.getImages(request)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _imageSearchState.value = SearchState.SUCCESS }
            .doOnError { _imageSearchState.value = SearchState.FAIL }
            .subscribe({ response ->
                updateDocuments(response.githubDocuments)
            }, { throwable ->
                handlingImageSearchError(throwable)
            })
            .disposeByOnCleared()
    }

    private fun updateDocuments(
        receivedImageDocuments: List<UserDocument>
    ) {
        _userDocuments.apply {
            addAll(receivedImageDocuments)
        }.also {
            if (it.isEmpty()) {
                updateShowMessage(R.string.success_image_search_no_result)
            }
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

    fun onClickFavorite(userDocument: UserDocument) {
        mUserDocument = userDocument.copy()
        mUserDocument?.let { document ->
            document.isFavorite = document.isFavorite.not()
            if (document.isFavorite) {
                saveFavoriteToRepository(document)
            } else {
                deleteFavoriteFromRepository(document)
            }
        }
    }


    private fun saveFavoriteToRepository(target: UserDocument) {
        userRepository.saveFavoriteImage(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateShowMessage(R.string.success_favorite_save)
            }, { throwable ->
                Log.d("gg", throwable.message)
            })
            .disposeByOnCleared()
    }

    private fun deleteFavoriteFromRepository(target: UserDocument) {
        userRepository.deleteFavoriteImage(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateShowMessage(R.string.success_favorite_delete)
            }, { throwable ->
                handlingImageSearchError(throwable)
            })
            .disposeByOnCleared()
    }

}
