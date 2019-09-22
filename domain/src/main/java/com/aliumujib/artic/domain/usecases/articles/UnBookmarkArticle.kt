package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.CompletableUseCase
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import io.reactivex.Completable
import javax.inject.Inject

class UnBookmarkArticle @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkArticle.Params>(postExecutionThread) {


    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) {
            throw IllegalStateException("Your params can't be null for this use case")
        }
        return articlesRepository.unBookmarkArticle(params.articleId)
    }


    data class Params constructor(val articleId: Int) {
        companion object {
            fun make(articleId: Int): Params {
                return Params(articleId)
            }
        }
    }

}