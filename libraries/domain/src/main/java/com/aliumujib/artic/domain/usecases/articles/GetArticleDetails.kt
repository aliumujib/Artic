package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleDetails @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetArticleDetails.Params, Article>(postExecutionThread) {

    override fun build(params: Params?): Flow<Article> {
        if (params == null) throw IllegalStateException("Your params can't be null for this use case")
        return this.articlesRepository.getArticleById(params.id)
    }


    data class Params constructor(val id: Int) {
        companion object {
            fun make(id: Int): Params {
                return Params(id)
            }
        }
    }
}