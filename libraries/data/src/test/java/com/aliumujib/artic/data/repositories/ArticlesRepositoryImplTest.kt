package com.aliumujib.artic.data.repositories

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.mapper.AuthorEntityMapper
import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.remote.api.WordPressAPI
import com.aliumujib.artic.data.repositories.articles.ArticlesRepositoryImpl
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ArticlesRepositoryImplTest {

    @Mock
    lateinit var articlesRemote: IArticlesRemote

    @Mock
    lateinit var articlesCache: IArticlesCache

    var authorEntityMapper: AuthorEntityMapper = AuthorEntityMapper()
    var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()

    lateinit var articlesEntityMapper: ArticleEntityMapper

    lateinit var articlesRepositoryImpl: ArticlesRepositoryImpl


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        articlesEntityMapper = ArticleEntityMapper(authorEntityMapper, categoryEntityMapper)

        articlesRepositoryImpl = ArticlesRepositoryImpl(
            articlesRemote,
            articlesCache,
            articlesEntityMapper
        )

        stubArticleRemoteResponse()
        stubArticleCacheResponse()
        stubBookmarkArticleCompletion()
        stubUnBookmarkArticleCompletion()
    }


    @Test
    fun `check that calling getArticles with noInternet flag as false fetches from cache`() {
        articlesRepositoryImpl.getArticles(0, false).test()
        verify(articlesCache).getArticles()
    }

    @Test
    fun `check that calling getArticles with noInternet flag as true fetches from network`() {
        articlesRepositoryImpl.getArticles(0, true).test()
        verify(articlesRemote).getArticles(0)
    }

    @Test
    fun `check that bookmarking an article completes`() {
        var testObserver = articlesRepositoryImpl.bookmarkArticle(any()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `check that unbookmarking an article completes`() {
        var testObserver = articlesRepositoryImpl.unBookmarkArticle(any()).test()
        testObserver.assertComplete()
    }


    fun stubBookmarkArticleCompletion() {
        whenever(articlesRepositoryImpl.bookmarkArticle(any())).thenReturn(Completable.complete())
    }

    fun stubUnBookmarkArticleCompletion() {
        whenever(articlesRepositoryImpl.unBookmarkArticle(any())).thenReturn(Completable.complete())
    }

    fun stubArticleCacheResponse() {
        whenever(articlesCache.getCachedArticles())
            .thenReturn(Observable.just(DummyDataFactory.makeArticlesEntitiesList(10)))
    }

    fun stubArticleRemoteResponse() {
        whenever(articlesRemote.getArticles(any())).thenReturn(
            Observable.just(
                DummyDataFactory.makeArticlesEntitiesList(
                    10
                )
            )
        )
    }
}