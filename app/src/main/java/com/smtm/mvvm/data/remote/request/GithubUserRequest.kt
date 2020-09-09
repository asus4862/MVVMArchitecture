package com.smtm.mvvm.data.remote.request

/**
 */
data class GithubUserRequest(
    val keyword: String,
    val pageNumber: Int,
    val requiredSize: Int,
    val isFirstRequest: Boolean
)