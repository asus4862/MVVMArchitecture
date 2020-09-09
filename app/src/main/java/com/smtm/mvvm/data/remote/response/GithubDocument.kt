package com.smtm.mvvm.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 */
data class GithubDocument(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatar_url: String?
)