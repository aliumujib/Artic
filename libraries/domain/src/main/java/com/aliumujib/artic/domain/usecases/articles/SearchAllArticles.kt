package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAllArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<SearchAllArticles.Params, List<Article>>(postExecutionThread) {

    override fun build(params: Params?): Flow<List<Article>> {
        if (params == null) {
            throw IllegalStateException("Your params can't be null for this use case")
        }

        if(params.query.isEmpty()){
            throw IllegalStateException("Your query cannot be empty")
        }

        return articlesRepository.searchArticles(params.query, params.page)
    }


    data class Params constructor(val query: String, val page: Int) {
        companion object {
            fun make(query: String, page: Int): Params {
                return Params(query, page)
            }
        }
    }
}