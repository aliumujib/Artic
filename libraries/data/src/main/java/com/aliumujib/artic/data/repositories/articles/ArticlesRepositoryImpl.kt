package com.aliumujib.artic.data.repositories.articles

import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesRemote: IArticlesRemote,
    private val articlesCache: IArticlesCache,
    private val articleEntityMapper: ArticleEntityMapper
) : IArticlesRepository {

    private val bookmarksMap: HashMap<Int, Boolean> = HashMap()

    override fun getArticles(refresh: Boolean, page: Int): Flow<List<Article>> {
        return flow {
            if (articlesCache.isArticlesCacheExpired()) {
                articlesCache.clearArticles()
            } else if (articlesCache.isCacheEmpty().not() && refresh) {
                val cachedData = articlesCache.getArticles().first()
                updateBookmarkMap(cachedData)
                emit(articleEntityMapper.mapFromEntityList(cachedData))
            }
            val articles: List<ArticleEntity> = articlesRemote.getArticles(page)
            updateArticleList(articles)
            if (page == 1 && refresh) {
                articlesCache.saveArticles(articles)
            }
            emit(articleEntityMapper.mapFromEntityList(articles))
        }
    }

    private fun updateArticleList(list: List<ArticleEntity>) {
        list.forEach {
            it.isBookmarked = bookmarksMap[it.id] ?: false
        }
    }

    private fun updateBookmarkMap(list: List<ArticleEntity>) {
        list.forEach {
            bookmarksMap[it.id] = it.isBookmarked
        }
    }

    override fun getArticleById(articleId: Int): Flow<Article> {
        return flow {
            val articles = articlesRemote.getArticle(articleId)
            emit(articleEntityMapper.mapFromEntity(articles))
        }
    }

    override suspend fun bookmarkArticle(article: Article) {
        articlesCache.setArticleAsBookmarked(articleEntityMapper.mapToEntity(article))
    }

    override suspend fun unBookmarkArticle(articleId: Int) {
        return articlesCache.setArticleAsNotBookmarked(articleId)
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articlesCache.getBookmarkedArticles()
            .onEach {
                updateBookmarkMap(it)
            }
            .map {
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
