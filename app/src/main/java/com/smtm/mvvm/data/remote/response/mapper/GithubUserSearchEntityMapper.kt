package com.smtm.mvvm.data.remote.response.mapper

import com.smtm.mvvm.data.remote.response.GithubUserResponse
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.data.repository.user.model.UserResponse


/**
 */
object GithubUserSearchEntityMapper {

    fun fromEntity(remoteEntity: GithubUserResponse): UserResponse {
        return remoteEntity.run {
            val userDocumentList = githubDocuments.map {
                UserDocument(
                    "${it.avatar_url}",
                    it.id,
                    it.login?:"",
                    it.avatar_url?:"",
                    false
                )
            }

            UserResponse(userDocumentList)
        }
    }

}