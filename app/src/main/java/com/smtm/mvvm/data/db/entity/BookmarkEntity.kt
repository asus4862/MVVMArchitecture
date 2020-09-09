package com.smtm.mvvm.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.smtm.mvvm.data.db.converter.DateConverter

/**
 */
@Entity(tableName = "Bookmark")
@TypeConverters(DateConverter::class)
data class BookmarkEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "book_id") val book_id: Long,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatar_url") val avatar_url: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)