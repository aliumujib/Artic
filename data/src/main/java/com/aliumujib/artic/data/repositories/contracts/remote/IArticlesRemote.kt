package com.aliumujib.artic.data.repositories.contracts.remote

import com.aliumujib.artic.data.model.ArticleEntity
import io.reactivex.Observable

interface IArticlesRemote {

    fun getArticles(page: Int): Observable<List<ArticleEntity>>

    fun searchArticles(search: String, page: Int): Observable<List<ArticleEntity>>

}