package com.smtm.mvvm.data.repository.user.model

import android.os.Parcel
import android.os.Parcelable

/**
 **/
data class UserDocument(
    val id: String,
    val book_id: Long,
    val login: String,
    val avatar_url: String,
    var isFavorite: Boolean
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readLong(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readInt() == 1
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeLong(book_id)
        writeString(login)
        writeString(avatar_url)
        writeInt((if (isFavorite) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserDocument> = object : Parcelable.Creator<UserDocument> {
            override fun createFromParcel(source: Parcel): UserDocument = UserDocument(source)
            override fun newArray(size: Int): Array<UserDocument?> = arrayOfNulls(size)
        }
    }
}