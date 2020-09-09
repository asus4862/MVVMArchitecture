package com.smtm.mvvm.data.repository.user

import com.smtm.mvvm.data.remote.request.ConnectRequest
import com.smtm.mvvm.data.remote.request.GithubUserRequest
import com.smtm.mvvm.data.remote.request.TokenRequest
import com.smtm.mvvm.data.remote.response.ConnectResponse
import com.smtm.mvvm.data.remote.response.TokenResponse
import com.smtm.mvvm.data.repository.user.model.UserResponse
import io.reactivex.Single

/**
 **/
interface UserRemoteDataSource {

    fun getUsers(userSearchRequest: GithubUserRequest): Single<UserResponse>


    fun getToken(tokenRequest: TokenRequest): Single<TokenResponse>

    fun getConnect(authorization: String, connectRequest: ConnectRequest): Single<ConnectResponse>

}