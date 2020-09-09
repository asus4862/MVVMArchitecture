package com.smtm.mvvm.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 */
data class GithubUserResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val githubDocuments: ArrayList<GithubDocument>
)