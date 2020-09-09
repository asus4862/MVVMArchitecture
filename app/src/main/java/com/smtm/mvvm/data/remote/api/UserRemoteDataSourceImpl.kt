package com.smtm.mvvm.data.remote.api


import com.smtm.mvvm.data.remote.request.ConnectRequest
import com.smtm.mvvm.data.remote.request.GithubUserRequest
import com.smtm.mvvm.data.remote.request.TokenRequest
import com.smtm.mvvm.data.remote.response.ConnectResponse
import com.smtm.mvvm.data.remote.response.TokenResponse
import com.smtm.mvvm.data.remote.response.mapper.GithubUserSearchEntityMapper
import com.smtm.mvvm.data.repository.user.UserRemoteDataSource
import com.smtm.mvvm.data.repository.user.model.UserResponse
import io.reactivex.Single

/**
 **/
class UserRemoteDataSourceImpl(
    private val githubSearchApi: SearchAPI
) : UserRemoteDataSource {

    override fun getUsers(userSearchRequest: GithubUserRequest): Single<UserResponse> {
        return userSearchRequest.run {
            githubSearchApi.search(keyword, pageNumber, requiredSize)
                .map { GithubUserSearchEntityMapper.fromEntity(it) }
        }
    }

    override fun getToken(tokenRequest: TokenRequest): Single<TokenResponse> {
        return tokenRequest.run {
            githubSearchApi.token(apiKey, apiSecret)
        }
    }

    override fun getConnect(authorization: String, connectRequest: ConnectRequest): Single<ConnectResponse> {
        return connectRequest.run {
            githubSearchApi.connect(authorization, connectRequest)
        }
    }
}