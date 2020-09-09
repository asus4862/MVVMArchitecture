package com.smtm.mvvm.data.remote.request

import com.google.gson.annotations.SerializedName

/**
 */
data class ConnectRequest(
    val userId: String,
    val publicRoomId: String,
    var userData: UserData,
    var template: Template,
    var appName: String
)
