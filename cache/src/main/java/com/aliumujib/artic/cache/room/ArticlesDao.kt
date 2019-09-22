package com.aliumujib.artic.cache.room

import androidx.room.*
import com.aliumujib.artic.cache.models.ArticleCacheModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(app: List<ArticleCacheModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(app: ArticleCacheModel)

    @Query("SELECT * FROM ARTICLES WHERE id =:id")
    fun getArticle(id: Int): ArticleCacheModel?

    @Transaction
    fun unBookmarkArticle(id: Int) {
        val article = getArticle(id)
        article?.let {
            it.isBookmarked = false
            insert(it)
        }
    }

    @Query("SELECT COUNT(*) FROM ARTICLES ORDER BY date DESC ")
    fun getAllCachedArticlesCount(): Int

    @Query("SELECT * FROM ARTICLES ORDER BY date DESC ")
    fun getAllCachedArticles(): Flowable<List<ArticleCacheModel>>

    @Query("SELECT * FROM ARTICLES WHERE isBookmarked =  1 ORDER BY date DESC ")
    fun getAllBookmarkedArticles(): Flowable<List<ArticleCacheModel>>

    @Query("DELETE FROM ARTICLES WHERE id =:id")
    fun deleteArticle(id: Int)

    @Query("DELETE FROM ARTICLES")
    fun deleteAllArticles(): Completable
}