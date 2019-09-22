package com.aliumujib.artic.domain.interactors.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.interactors.ObservableUseCase
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<GetAllArticles.Params, List<Article>>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Article>> {
        if (params == null) throw IllegalStateException("Your params can't be null for this use case")
        return this.articlesRepository.getArticles(params.page, params.isConnected)
    }

    data class Params constructor(val isConnected: Boolean, val page: Int) {
        companion object {
            fun make(isConnected: Boolean, page: Int): Params {
                return Params(isConnected, page)
            }
        }
    }
}