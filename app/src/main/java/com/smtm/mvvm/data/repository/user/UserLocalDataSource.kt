package com.smtm.mvvm.data.repository.user


import com.smtm.mvvm.data.repository.user.model.UserDocument
import io.reactivex.Completable
import io.reactivex.Single

/**
 */
interface UserLocalDataSource {

    fun saveFavoriteUserDocument(imageDocument: UserDocument): Completable

    fun deleteFavoriteUserDocument(imageDocument: UserDocument): Completable

    fun getAllFavoriteUsers(): Single<List<UserDocument>>

}