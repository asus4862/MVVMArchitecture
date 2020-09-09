package com.smtm.mvvm.data.remote.api

import com.smtm.mvvm.data.remote.request.ConnectRequest
import com.smtm.mvvm.data.remote.response.ConnectResponse
import com.smtm.mvvm.data.remote.response.GithubUserResponse
import com.smtm.mvvm.data.remote.response.TokenResponse
import io.reactivex.Single
import retrofit2.http.*


/**
 */
interface SearchAPI {
    @GET("/search/users")
    fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<GithubUserResponse>


    // 토큰 발금
    @FormUrlEncoded
    @POST("/authentication/token")
    fun token(
        @Field("apiKey") apiKey: String,
        @Field("apiSecret") apiSecret: String
    ): Single<TokenResponse>


//    @FormUrlEncoded
//    @POST("/connection/in")
//    fun connect(
//        @Header("Authorization") token: String?,
//        @Field("userId") userId: String?,
//        @Field("publicRoomId") publicRoomId: String?,
//        @Field("userData") userData: com.smtm.mvvm.data.remote.request.UserData
//    ): Single<ConnectResponse>

    @POST("/connection/in")
    fun connect(
        @Header("Authorization") token: String?,
        @Body connectRequest: ConnectRequest
    ): Single<ConnectResponse>
}