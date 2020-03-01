package com.aliumujib.artic.domain.repositories.comments

import com.aliumujib.artic.domain.models.Comment
import kotlinx.coroutines.flow.Flow

interface ICommentsRepository {

    fun getCommentsForArticle(parentId: Int): Flow<List<Comment>>

    fun commentOnArticle(comment: Comment)

}