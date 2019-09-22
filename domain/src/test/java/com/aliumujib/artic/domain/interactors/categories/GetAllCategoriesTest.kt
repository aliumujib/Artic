package com.aliumujib.artic.domain.interactors.categories

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.interactors.articles.GetAllArticles
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllCategoriesTest {

    private lateinit var getCategories: GetAllCategories
    @Mock
    lateinit var categoriesRepository: ICategoriesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategories = GetAllCategories(categoriesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling getProjects completes`() {
        stubGetCategories(Observable.just(ArticleDataFactory.makeCategoryList(2)))
        val testObserver = getCategories.buildUseCaseObservable(GetAllCategories.Params.make(
            any())).test()
        testObserver.assertComplete()
    }

    @Test
    fun `confirm that calling getProjects returns data`() {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(Observable.just(list))
        val testObserver = getCategories.buildUseCaseObservable(GetAllCategories.Params.make(any())).test()
        testObserver.assertValue(list)
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using getProjects with params throws an exception`() {
        val projects = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(Observable.just(projects))
        val testObserver = getCategories.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetCategories(observable: Observable<List<Category>>) {
        whenever(categoriesRepository.getCategories(any()))
            .thenReturn(observable)
    }

}