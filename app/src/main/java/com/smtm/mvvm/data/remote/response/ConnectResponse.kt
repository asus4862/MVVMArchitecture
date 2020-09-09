package com.smtm.mvvm.data.remote.response

import com.google.gson.annotations.SerializedName
import com.smtm.mvvm.data.remote.request.UserData

/**
 */
data class ConnectResponse(
    @SerializedName("html") val html: String,
    @SerializedName("roomId") val roomId: String,
    @SerializedName("busy") val publicRoomId: Boolean
)