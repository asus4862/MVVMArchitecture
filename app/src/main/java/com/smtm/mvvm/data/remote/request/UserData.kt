package com.smtm.mvvm.data.remote.request

import com.google.gson.annotations.SerializedName

/**
 */
data class UserData(
    @SerializedName("type")
    var type: String,
    @SerializedName("listenType")
    var listenType: String,
    @SerializedName("script")
    var script: String
)