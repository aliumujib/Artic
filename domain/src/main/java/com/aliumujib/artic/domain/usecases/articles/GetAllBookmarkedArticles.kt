package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.ObservableUseCase
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.eyowo.android.core.domain.usecases.FlowableUseCase
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class GetAllBookmarkedArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<Nothing, List<Article>>(postExecutionThread) {

    public override fun buildUseCaseFlowable(params: Nothing?): Flowable<List<Article>> {
        return articlesRepository.getBookmarkedArticles()
    }

}