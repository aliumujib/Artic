package com.aliumujib.artic.data.repositories.articles

import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ArticlesRepoImpl @Inject constructor(
    private val articlesRemote: IArticlesRemote,
    private val articlesCache: IArticlesCache,
    private val articleEntityMapper: ArticleEntityMapper
) : IArticlesRepository {

    override fun getArticles(page: Int, isInternetAvailable: Boolean): Flow<List<Article>> {
        return if (isInternetAvailable) {
            flow {
                val articles: List<ArticleEntity> = articlesRemote.getArticles(page)
                if (page == 1) {
                    articlesCache.saveArticles(articles)
                }
                emit(articleEntityMapper.mapFromEntityList(articles))
            }
        } else {
            articlesCache.getArticles().map {
                articleEntityMapper.mapFromEntityList(it)
            }
        }
    }


    override suspend fun bookmarkArticle(article: Article) {
        articlesCache.setArticleAsBookmarked(articleEntityMapper.mapToEntity(article))
    }

    override suspend fun unBookmarkArticle(articleId: Int) {
        return articlesCache.setArticleAsNotBookmarked(articleId)
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articlesCache.getBookmarkedArticles().map {
            articleEntityMapper.mapFromEntityList(it)
        }
    }

    override fun searchArticles(query: String, page: Int): Flow<List<Article>> {
        return flow {
            val articles: List<ArticleEntity> =
                articlesRemote.searchArticles(search = query, page = page)
            emit(articleEntityMapper.mapFromEntityList(articles))
        }
    }

}
