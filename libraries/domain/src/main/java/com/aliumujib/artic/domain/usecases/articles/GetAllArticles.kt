package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.exceptions.NoParamsException
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetAllArticles.Params, List<Article>>(postExecutionThread) {

    override fun build(params: Params?): Flow<List<Article>> {
        if (params == null) throw NoParamsException()
        return this.articlesRepository.getArticles(params.refresh, params.page)
    }


    data class Params constructor(val refresh: Boolean, val page: Int) {
        companion object {
            fun make(refresh: Boolean, page: Int): Params {
                return Params(refresh, page)
            }
        }
    }
}