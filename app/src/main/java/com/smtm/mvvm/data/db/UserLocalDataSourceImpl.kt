package com.smtm.mvvm.data.db

import com.smtm.mvvm.data.db.dao.BookmarkDao
import com.smtm.mvvm.data.db.entity.mapper.UserDocumentEntityMapper
import com.smtm.mvvm.data.db.transformer.error.CompletableExceptionTransformer
import com.smtm.mvvm.data.db.transformer.error.SingleExceptionTransformer
import com.smtm.mvvm.data.repository.user.UserLocalDataSource
import com.smtm.mvvm.data.repository.user.model.UserDocument
import io.reactivex.Completable
import io.reactivex.Single

/**
 */
class UserLocalDataSourceImpl(
    private val bookDAO: BookmarkDao
) : UserLocalDataSource {
    override fun saveFavoriteUserDocument(userDocument: UserDocument): Completable {
        val imageDocumentEntity = UserDocumentEntityMapper.toEntity(userDocument)
        return bookDAO.insertOrUpdateImageDocument(imageDocumentEntity)
            .compose(CompletableExceptionTransformer())
    }

    override fun deleteFavoriteUserDocument(userDocument: UserDocument): Completable {
        return bookDAO.deleteImageDocument(userDocument.id)
            .compose(CompletableExceptionTransformer())
    }

    override fun getAllFavoriteUsers(): Single<List<UserDocument>> {
        return bookDAO.selectAllImageDocument()
            .map { UserDocumentEntityMapper.fromEntityList(it) }
            .compose(SingleExceptionTransformer())
    }
}