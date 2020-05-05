/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    fun getAllCachedArticlesByDate(): Flow<List<ArticleCacheModel>>

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
