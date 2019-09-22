package com.aliumujib.artic.data.repositories.articles

import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRepoImpl @Inject constructor(
    var articlesRemote: IArticlesRemote,
    var articlesCache: IArticlesCache,
    var articleEntityMapper: ArticleEntityMapper
) : IArticlesRepository {

    override fun getArticles(page: Int, isInternetAvailable: Boolean): Observable<List<Article>> {
        return if (isInternetAvailable) {
            articlesRemote.getArticles(page)
                .map {
                    articleEntityMapper.mapFromEntityList(it)
                }
        } else {
            articlesCache.getArticles().map {
                articleEntityMapper.mapFromEntityList(it)
            }.toObservable()
        }
    }

    override fun bookmarkArticle(article: Article): Completable {
        return articlesCache.setArticleAsBookmarked(articleEntityMapper.mapToEntity(article))
    }

    override fun unBookmarkArticle(articleId: Int): Completable {
        return articlesCache.setArticleAsNotBookmarked(articleId)
    }

    override fun getBookmarkedArticles(): Flowable<List<Article>> {
        return articlesCache.getBookmarkedArticles().map {
            articleEntityMapper.mapFromEntityList(it)
        }
    }

    override fun searchArticles(query: String, page: Int): Observable<List<Article>> {
        return articlesRemote.searchArticles(search = query, page = page).map {
            articleEntityMapper.mapFromEntityList(it)
        }
    }

}
