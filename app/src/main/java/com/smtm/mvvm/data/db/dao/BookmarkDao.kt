package com.smtm.mvvm.data.db.dao

import androidx.room.*
import com.smtm.mvvm.data.db.entity.BookmarkEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 */
@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateImageDocument(imageDocumentEntity: BookmarkEntity): Completable

    @Query("DELETE FROM Bookmark WHERE id = :id")
    fun deleteImageDocument(id: String): Completable

    @Query("SELECT * FROM Bookmark")
    fun selectAllImageDocument(): Single<List<BookmarkEntity>>
}