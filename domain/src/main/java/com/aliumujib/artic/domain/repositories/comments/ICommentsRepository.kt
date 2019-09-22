package com.aliumujib.artic.domain.repositories.comments

import com.aliumujib.artic.domain.models.Comment
import io.reactivex.Completable
import io.reactivex.Observable


interface ICommentsRepository {

    fun getCommentsForArticle(parentId: Int): Observable<List<Comment>>

    fun commentOnArticle(comment: Comment): Completable

}