package com.aliumujib.artic.domain.usecases.categories


import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesForCategory @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetArticlesForCategory.Params, List<Article>>(postExecutionThread) {

    override fun build(params: Params?): Flow<List<Article>> {
        if (params == null) {
            throw IllegalStateException("Your params can't be null for this use case")
        }
        return articlesRepository.getArticlesByCategoryId(params.categoryId, params.page)
    }

    data class Params constructor(val page: Int, val categoryId: Int) {
        companion object {
            fun make(page: Int, categoryId: Int): Params {
                return Params(page, categoryId)
            }
        }
    }

}