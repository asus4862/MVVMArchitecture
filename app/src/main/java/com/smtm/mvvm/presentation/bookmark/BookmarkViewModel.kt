package com.smtm.mvvm.presentation.bookmark

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.common.livedata.SingleLiveEvent
import com.smtm.mvvm.presentation.base.BaseViewModel
import com.smtm.mvvm.data.repository.user.UserRepository
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.extension.add
import com.smtm.mvvm.extension.removeFirst
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 */
class BookmarkViewModel(application: Application, private val userRepository: UserRepository): BaseViewModel(application) {
//    val items: LiveData<PagedList<BookmarkEntity>> = LivePagedListBuilder(dao.findAll(),  /* page size */ 10).build()
//
//    fun delete(bookmarkEntity: BookmarkEntity) = ioThread { dao.delete(bookmarkEntity) }

    private val _favoriteImages = MutableLiveData<MutableList<UserDocument>>()
    val favoriteImages: LiveData<List<UserDocument>> = Transformations.map(_favoriteImages) { it?.toList() }

    private val _moveToDetailScreenEvent = SingleLiveEvent<UserDocument>()
    val moveToDetailScreenEvent: LiveData<UserDocument> = _moveToDetailScreenEvent

    init {
//        observeChangingFavoriteImage()
    }

    fun loadFavoriteImageList() {
        userRepository.getAllFavoriteImages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ receivedFavoriteImages ->
                _favoriteImages.value = receivedFavoriteImages.toMutableList()
            }, { throwable ->
                Log.d("", throwable.message)
            })
            .disposeByOnCleared()
    }

    fun onClickDel(userDocument: UserDocument) {
        deleteFavoriteFromRepository(userDocument)
//        _moveToDetailScreenEvent.value = userDocument
    }

    private fun observeChangingFavoriteImage() {
        userRepository.observeChangingFavoriteImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ changedImageDocument ->
                with(changedImageDocument) {
                    _favoriteImages.removeFirst { it.id == id }
                    if(isFavorite) {
                        _favoriteImages.add(this)
                    }
                }
            }, {
                Log.d("", it.message)
            })
            .disposeByOnCleared()
    }


    private fun deleteFavoriteFromRepository(target: UserDocument) {
        userRepository.deleteFavoriteImage(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateImageDocument(target)
                updateShowMessage(R.string.success_favorite_delete)
            }, { throwable ->
                Log.d("", throwable.message)
            })
            .disposeByOnCleared()
    }
    private fun updateImageDocument(target: UserDocument) {
//        userDocument = target
//        _isFavorite.value = target.isFavorite
        observeChangingFavoriteImage()
    }
}
