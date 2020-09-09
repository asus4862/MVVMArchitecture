package com.smtm.mvvm.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 */
data class TokenResponse(
    @SerializedName("token") val token: String,
    @SerializedName("exp") val exp: String
)