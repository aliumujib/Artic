package com.aliumujib.artic.data.repositories.contracts.repo

import com.aliumujib.artic.data.model.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface IArticlesDataStore {

    fun getArticles(page: Int, isOnline: Boolean): Observable<List<ArticleEntity>>

    fun saveArticles(projects: List<ArticleEntity>): Completable

    fun clearArticles(): Completable

    fun getBookmarkedArticles(): Observable<List<ArticleEntity>>

    fun setArticleAsBookmarked(articleId: Int): Completable

    fun setArticleAsNotBookmarked(articleId: Int): Completable

}