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
package com.aliumujib.artic.data.repositories.categories

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class CategoriesRepositoryImplTest {

    @MockK(relaxed = true)
    lateinit var categoriesRemote: ICategoriesRemote
    private var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()
    private lateinit var categoriesRepositoryImpl: CategoriesRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        categoriesRepositoryImpl = CategoriesRepositoryImpl(
            categoriesRemote,
            categoryEntityMapper
        )

    }


    @Test
    fun `check that calling getCategories with calls remote only once`() = runBlocking {
        stubCategoryRemoteResponse(DummyDataFactory.makeRandomCategoryEntityList(10))
        val page :Int = randomBuild()
        val count :Int = randomBuild()
        categoriesRepositoryImpl.getCategories(page, count).first()
        coVerify(exactly = 1) {
            categoriesRemote.getCategories(page, count)
        }
    }

    @Test
    fun `check that calling getCategories with returns an data when remote call succeeds`() = runBlocking {
        val response = DummyDataFactory.makeRandomCategoryEntityList(10)
        stubCategoryRemoteResponse(response)
        val argument :Int = randomBuild()
        val count :Int = randomBuild()
        val data = categoriesRepositoryImpl.getCategories(argument, count).first()
        assertThat(categoryEntityMapper.mapFromEntityList(response)).isEqualTo(data)
    }

    @Test(expected = SocketTimeoutException::class)
    fun `check that calling getCategories with returns an error when one occurs in the remote call`() = runBlocking {
        stubCategoryRemoteError(SocketTimeoutException())
        val argument :Int = randomBuild()
        val count :Int = randomBuild()
        val error = categoriesRepositoryImpl.getCategories(argument, count).first()
        assertThat(error).isInstanceOf(SocketTimeoutException::class.java)
    }

    private fun stubCategoryRemoteError(error: Exception) {
        coEvery { categoriesRemote.getCategories(any(), any()) } throws  error
    }

    private fun stubCategoryRemoteResponse(categories: List<CategoryEntity>) {
        coEvery { categoriesRemote.getCategories(any(), any()) } returns categories
    }
}
