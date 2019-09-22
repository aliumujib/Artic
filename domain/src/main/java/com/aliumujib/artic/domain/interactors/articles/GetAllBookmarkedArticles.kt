package com.aliumujib.artic.domain.interactors.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.interactors.ObservableUseCase
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllBookmarkedArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Nothing, List<Article>>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Article>> {
        return articlesRepository.getBookmarkedArticles()
    }

}