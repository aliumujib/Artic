package com.aliumujib.artic.domain.usecases.categories

import com.aliumujib.artic.domain.exceptions.NoParamsException
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.testutils.ArticleDataFactory
import com.aliumujib.artic.domain.testutils.TestPostExecutionThreadImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            categoriesRepository.getCategories(any())
        }
    }

    @Test(expected = NoParamsException::class)
    fun `confirm that using getProjects with params throws an exception`() = runBlockingTest {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(flow {
            emit(list)
        })
        getCategories.build().first()
        coVerify(exactly = 0) {
            categoriesRepository.getCategories(any())
        }
    }

    private fun stubGetCategories(flow: Flow<List<Category>>) {
        every {
            categoriesRepository.getCategories(any())
        } returns flow
    }

}