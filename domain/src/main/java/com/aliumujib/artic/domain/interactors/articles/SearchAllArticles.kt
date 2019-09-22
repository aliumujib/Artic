package com.aliumujib.artic.domain.interactors.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.interactors.ObservableUseCase
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchAllArticles @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<SearchAllArticles.Params, List<Article>>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Article>> {
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