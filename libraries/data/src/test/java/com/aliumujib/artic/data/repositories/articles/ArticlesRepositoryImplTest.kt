package com.aliumujib.artic.data.repositories.articles

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.mapper.AuthorEntityMapper
import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.articles.ArticlesRepositoryImpl
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.aliumujib.artic.domain.models.Article
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class ArticlesRepositoryImplTest {

    @MockK(relaxed = true)
    lateinit var articlesRemote: IArticlesRemote

    @MockK(relaxed = true)
    lateinit var articlesCache: IArticlesCache

    private var authorEntityMapper: AuthorEntityMapper = AuthorEntityMapper()
    private var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()
    private lateinit var articlesEntityMapper: ArticleEntityMapper

    private lateinit var articlesRepositoryImpl: ArticlesRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        articlesEntityMapper = ArticleEntityMapper(authorEntityMapper, categoryEntityMapper)

        articlesRepositoryImpl = ArticlesRepositoryImpl(
            articlesRemote,
            articlesCache,
            articlesEntityMapper
        )

        stubArticleRemoteResponse(DummyDataFactory.makeArticlesEntitiesList(10))
        stubArticleCacheResponse(DummyDataFactory.makeArticlesEntitiesList(10))
    }


    @Test
    fun `check that calling getArticles with calls cache when cahce is not empty is empty`() = runBlocking {
        stubGetArticles(DummyDataFactory.makeArticlesEntitiesList(10))
        stubEmptyCacheResponse(randomBuild())
        articlesRepositoryImpl.getArticles(randomBuild(), randomBuild()).first()
        coVerify(exactly = 0) {
            articlesCache.getCachedArticles()
        }
    }

    @Test
    fun `check that calling getArticles with noInternet flag as true fetches from network`() = runBlocking {
        stubGetArticles(DummyDataFactory.makeArticlesEntitiesList(10))
        stubEmptyCacheResponse(randomBuild())
        articlesRepositoryImpl.getArticles(randomBuild(), randomBuild()).first()
        coVerify(exactly = 1) {
            articlesRemote.getArticles(any())
        }
    }

    @Test
    fun `check that bookmarking an article completes`() = runBlocking {
        val article = DummyDataFactory.makeRandomArticle()
        article.isBookmarked = false
        stubBookmarkArticleCompletion(article)
        stubFindArticleFromCacheResponse(article, true)
        val result = articlesRepositoryImpl.bookmarkArticle(article)
        coVerify(exactly = 1) {
            articlesCache.setArticleAsBookmarked(any())
            articlesCache.findArticleById(any())
        }
        assertEquals(article.isBookmarked, false)
        assertEquals(result?.isBookmarked, true)
        assertNotEquals(article.isBookmarked, result?.isBookmarked)
    }

    @Test
    fun `check that unBookmarking an article completes`() = runBlocking {
        val article = DummyDataFactory.makeRandomArticle()
        article.isBookmarked = true
        stubUnBookmarkArticleCompletion(article)
        stubFindArticleFromCacheResponse(article, false)
        val result = articlesRepositoryImpl.unBookmarkArticle(article.id)
        coVerify(exactly = 1) {
            articlesCache.setArticleAsNotBookmarked(any())
            articlesCache.findArticleById(any())
        }
        assertEquals(article.isBookmarked, true)
        assertEquals(result?.isBookmarked, false)
        assertNotEquals(article.isBookmarked, result?.isBookmarked)
    }


    private fun stubBookmarkArticleCompletion(article: Article) {
        coEvery { articlesCache.setArticleAsBookmarked(articlesEntityMapper.mapToEntity(article)) } returns Unit
    }

    private fun stubUnBookmarkArticleCompletion(article: Article) {
        coEvery { articlesCache.setArticleAsNotBookmarked(article.id) } returns Unit
    }

    private fun stubGetArticles(articles: List<ArticleEntity>) {
        stubArticleCacheResponse(articles)
        stubArticleCacheResponse(articles)

    }

    private fun stubFindArticleFromCacheResponse(article: Article, isBookmarked: Boolean) {
        val result =  articlesEntityMapper.mapToEntity(article)
        result.isBookmarked = isBookmarked
        coEvery { articlesCache.findArticleById(article.id) } returns result
    }

    private fun stubEmptyCacheResponse(isEmpty: Boolean) {
        coEvery { articlesCache.isCacheEmpty() } returns isEmpty
    }

    private fun stubArticleCacheResponse(articles: List<ArticleEntity>) {
        coEvery { articlesCache.getCachedArticles() } returns flowOf(articles)
    }

    private fun stubArticleRemoteResponse(articles: List<ArticleEntity>) {
        coEvery { articlesRemote.getArticles(any()) } returns articles
    }
}