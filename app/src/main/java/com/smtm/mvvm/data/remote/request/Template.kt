package com.smtm.mvvm.data.remote.request

import com.google.gson.annotations.SerializedName

/**
 */
data class Template(
    @SerializedName("preset")
    var preset: String
)