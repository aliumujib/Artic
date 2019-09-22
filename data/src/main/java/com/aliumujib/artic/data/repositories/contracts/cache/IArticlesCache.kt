package com.aliumujib.artic.data.repositories.contracts.cache

import com.aliumujib.artic.data.model.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface IArticlesCache  {

    fun clearArticles(): Completable

    fun saveArticles(articles: List<ArticleEntity>)

    fun getArticles(): Flowable<List<ArticleEntity>>

    fun getBookmarkedArticles(): Flowable<List<ArticleEntity>>

    fun setArticleAsBookmarked(article: ArticleEntity): Completable

    fun setArticleAsNotBookmarked(articleId: Int): Completable

    fun areArticlesCached(): Boolean

    fun setLastCacheTime(lastCache: Long)

    fun isArticlesCacheExpired(): Boolean
}