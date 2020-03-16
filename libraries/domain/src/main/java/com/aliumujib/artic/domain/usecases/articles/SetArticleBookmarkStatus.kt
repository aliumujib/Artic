package com.aliumujib.artic.domain.usecases.articles


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.usecases.base.SuspendUseCase
import javax.inject.Inject

class SetArticleBookmarkStatus @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<SetArticleBookmarkStatus.Params, Article?>(postExecutionThread) {

    data class Params constructor(val article: Article, val isBookmarked: Boolean) {
        companion object {
            fun make(article: Article, isBookmarked: Boolean): Params {
                return Params(article, isBookmarked)
            }
        }
    }

    override suspend fun execute(params: Params?): Article? {
        if (params == null) {
            throw IllegalStateException("Your params can't be null for this use case")
        }
        return if(params.isBookmarked){
            articlesRepository.unBookmarkArticle(params.article.id)
        }else{
            articlesRepository.bookmarkArticle(params.article)
        }
    }


}