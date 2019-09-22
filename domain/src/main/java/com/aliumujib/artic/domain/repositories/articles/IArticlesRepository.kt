package com.aliumujib.artic.domain.repositories.articles

import com.aliumujib.artic.domain.models.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable


interface IArticlesRepository {

    fun getArticles(page: Int, isInternetAvailable: Boolean = true): Observable<List<Article>>

    fun bookmarkArticle(article: Article): Completable

    fun unBookmarkArticle(articleId: Int): Completable

    fun getBookmarkedArticles(): Flowable<List<Article>>

    fun searchArticles(query: String, page: Int): Observable<List<Article>>

}