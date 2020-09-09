package com.smtm.mvvm.data.db.entity.mapper

import com.smtm.mvvm.data.db.entity.BookmarkEntity
import com.smtm.mvvm.data.repository.user.model.UserDocument


/**
 *
 **/
object UserDocumentEntityMapper {

    fun toEntity(imageDocument: UserDocument): BookmarkEntity {
        return imageDocument.run {
            BookmarkEntity(
                id,
                book_id,
                login,
                avatar_url,
                isFavorite
            )
        }
    }

    fun fromEntityList(imageDocumentEntityList: List<BookmarkEntity>): List<UserDocument> {
        return imageDocumentEntityList.map { fromEntity(it) }
    }

    fun fromEntity(imageDocumentEntity: BookmarkEntity): UserDocument {
        return imageDocumentEntity.run {
            UserDocument(
                id,
                book_id,
                login,
                avatar_url,
                isFavorite
            )
        }
    }
}