package com.aliumujib.artic.cache.room

import androidx.room.*
import com.aliumujib.artic.cache.models.ArticleCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(app: List<ArticleCacheModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(app: ArticleCacheModel)

    @Query("SELECT * FROM ARTICLES WHERE id =:id")
    suspend  fun getArticle(id: Int): ArticleCacheModel?

    @Transaction
    suspend fun unBookmarkArticle(id: Int) {
        val article = getArticle(id)
        article?.let {
            it.isBookmarked = false
            insert(it)
        }
    }

    @Query("SELECT COUNT(*) FROM ARTICLES ORDER BY date DESC ")
    suspend fun getAllCachedArticlesCount(): Int

    @Query("SELECT * FROM ARTICLES ORDER BY date DESC ")
    fun getAllCachedArticles(): Flow<List<ArticleCacheModel>>

    @Query("SELECT * FROM ARTICLES WHERE date > :publishDate ORDER BY date DESC ")
    fun getAllValidCachedArticles(publishDate:Long): Flow<List<ArticleCacheModel>>

    @Query("SELECT * FROM ARTICLES WHERE isBookmarked =  1 ORDER BY date DESC ")
    fun getAllBookmarkedArticles(): Flow<List<ArticleCacheModel>>

    @Query("DELETE FROM ARTICLES WHERE id =:id")
    suspend fun deleteArticle(id: Int)

    @Query("DELETE FROM ARTICLES")
    suspend fun deleteAllArticles()
}