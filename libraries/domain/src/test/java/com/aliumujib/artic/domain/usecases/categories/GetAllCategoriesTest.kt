package com.aliumujib.artic.domain.usecases.categories

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.aliumujib.artic.domain.test.TestPostExecutionThreadImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test


class GetAllCategoriesTest {

    private lateinit var getCategories: GetAllCategories
    @MockK
    lateinit var categoriesRepository: ICategoriesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getCategories = GetAllCategories(categoriesRepository, postExecutionThread)
    }


    @Test
    fun `confirm that calling getProjects returns data`() = runBlockingTest {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(flow {
            emit(list)
        })
        val result = getCategories.build(GetAllCategories.Params.make(randomBuild())).first()
        assertThat(result, `is`((equalTo(list))))
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using getProjects with params throws an exception`() = runBlockingTest {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(flow {
            emit(list)
        })
        val result = getCategories.build().first()
        assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
    }

    private fun stubGetCategories(flow: Flow<List<Category>>) {
        every {
            categoriesRepository.getCategories(any())
        } returns flow
    }

}