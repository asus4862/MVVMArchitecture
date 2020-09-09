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
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 **/
class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    private val favoriteChangePublishSubject = PublishSubject.create<UserDocument>()

    override fun getImages(userSearchRequest: GithubUserRequest): Single<UserResponse> {
        return Single.zip(
            userRemoteDataSource.getUsers(userSearchRequest)
                .subscribeOn(Schedulers.io()),
            userLocalDataSource.getAllFavoriteUsers()
                .map { favoriteList -> favoriteList.associateBy({ it.id }, {it}) }
                .subscribeOn(Schedulers.io()),
            BiFunction { response: UserResponse, favoriteMap: Map<String, UserDocument> ->
                val newList = response.githubDocuments.map { favoriteMap[it.id] ?: it }
                UserResponse(newList)
            }
        ).subscribeOn(Schedulers.io())
    }

    override fun getAllFavoriteImages(): Single<List<UserDocument>> {
        return userLocalDataSource.getAllFavoriteUsers()
            .subscribeOn(Schedulers.io())
    }

    override fun saveFavoriteImage(imageDocument: UserDocument): Completable {
        return userLocalDataSource.saveFavoriteUserDocument(imageDocument)
            .doOnComplete { favoriteChangePublishSubject.onNext(imageDocument) }
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFavoriteImage(imageDocument: UserDocument): Completable {
        return userLocalDataSource.deleteFavoriteUserDocument(imageDocument)
            .doOnComplete { favoriteChangePublishSubject.onNext(imageDocument) }
            .subscribeOn(Schedulers.io())
    }

    override fun observeChangingFavoriteImage(): Observable<UserDocument> {
        return favoriteChangePublishSubject.subscribeOn(Schedulers.io())
    }


    override fun getToken(tokenRequest: TokenRequest): Single<TokenResponse> {
        return userRemoteDataSource.getToken(tokenRequest)
                .subscribeOn(Schedulers.io())
    }


    override fun getConnect(token: String, connectRequest: ConnectRequest): Single<ConnectResponse> {
        return userRemoteDataSource.getConnect(token,connectRequest)
            .subscribeOn(Schedulers.io())
    }
}