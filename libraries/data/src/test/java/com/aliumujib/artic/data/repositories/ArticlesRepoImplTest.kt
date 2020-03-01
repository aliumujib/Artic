package com.aliumujib.artic.data.repositories

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.mapper.AuthorEntityMapper
import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.remote.api.WordPressAPI
import com.aliumujib.artic.data.repositories.articles.ArticlesRepoImpl
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ArticlesRepoImplTest {

    @Mock
    lateinit var articlesRemote: IArticlesRemote

    @Mock
    lateinit var articlesCache: IArticlesCache

    var authorEntityMapper: AuthorEntityMapper = AuthorEntityMapper()
    var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()

    lateinit var articlesEntityMapper: ArticleEntityMapper

    lateinit var articlesRepoImpl: ArticlesRepoImpl


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        articlesEntityMapper = ArticleEntityMapper(authorEntityMapper, categoryEntityMapper)

        articlesRepoImpl = ArticlesRepoImpl(
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
        articlesRepoImpl.getArticles(0, false).test()
        verify(articlesCache).getArticles()
    }

    @Test
    fun `check that calling getArticles with noInternet flag as true fetches from network`() {
        articlesRepoImpl.getArticles(0, true).test()
        verify(articlesRemote).getArticles(0)
    }

    @Test
    fun `check that bookmarking an article completes`() {
        var testObserver = articlesRepoImpl.bookmarkArticle(any()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `check that unbookmarking an article completes`() {
        var testObserver = articlesRepoImpl.unBookmarkArticle(any()).test()
        testObserver.assertComplete()
    }


    fun stubBookmarkArticleCompletion() {
        whenever(articlesRepoImpl.bookmarkArticle(any())).thenReturn(Completable.complete())
    }

    fun stubUnBookmarkArticleCompletion() {
        whenever(articlesRepoImpl.unBookmarkArticle(any())).thenReturn(Completable.complete())
    }

    fun stubArticleCacheResponse() {
        whenever(articlesCache.getArticles())
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