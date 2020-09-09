package com.smtm.mvvm.data.repository.user

import com.smtm.mvvm.data.remote.request.ConnectRequest
import com.smtm.mvvm.data.remote.request.GithubUserRequest
import com.smtm.mvvm.data.remote.request.TokenRequest
import com.smtm.mvvm.data.remote.response.ConnectResponse
import com.smtm.mvvm.data.remote.response.TokenResponse
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.data.repository.user.model.UserResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 **/
interface UserRepository {

    fun getImages(userSearchRequest: GithubUserRequest): Single<UserResponse>

    fun getAllFavoriteImages(): Single<List<UserDocument>>

    fun saveFavoriteImage(imageDocument: UserDocument): Completable

    fun deleteFavoriteImage(imageDocument: UserDocument): Completable

    fun observeChangingFavoriteImage(): Observable<UserDocument>



    fun getToken(tokenRequest: TokenRequest): Single<TokenResponse>


    fun getConnect(token: String,tokenRequest: ConnectRequest): Single<ConnectResponse>
}