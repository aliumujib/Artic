/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    fun `confirm that calling getCategories returns data`() = runBlockingTest {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(flow {
            emit(list)
        })
        val page: Int = randomBuild()
        val count: Int = randomBuild()
        val result = getCategories.build(GetAllCategories.Params.make(page, count)).first()
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            categoriesRepository.getCategories(page, count)
        }
    }

    @Test(expected = NoParamsException::class)
    fun `confirm that using getCategories with params throws an exception`() = runBlockingTest {
        val list = ArticleDataFactory.makeCategoryList(10)
        stubGetCategories(flow {
            emit(list)
        })
        val page: Int = randomBuild()
        val count: Int = randomBuild()
        getCategories.build().first()
        coVerify(exactly = 0) {
            categoriesRepository.getCategories(page, count)
        }
    }

    private fun stubGetCategories(flow: Flow<List<Category>>) {
        every {
            categoriesRepository.getCategories(any(), any())
        } returns flow
    }

}
