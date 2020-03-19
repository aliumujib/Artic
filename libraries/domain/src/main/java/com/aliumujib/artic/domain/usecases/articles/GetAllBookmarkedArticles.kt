package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBookmarkedArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Nothing, List<Article>>(postExecutionThread) {


    override fun build(params: Nothing?): Flow<List<Article>> {
        return articlesRepository.getBookmarkedArticles()
    }


}