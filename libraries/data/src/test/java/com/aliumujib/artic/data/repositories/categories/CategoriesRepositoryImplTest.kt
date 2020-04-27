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
        val argument :Int = randomBuild()
        categoriesRepositoryImpl.getCategories(argument).first()
        coVerify(exactly = 1) {
            categoriesRemote.getCategories(argument)
        }
    }

    @Test
    fun `check that calling getCategories with returns an data when remote call succeeds`() = runBlocking {
        val response = DummyDataFactory.makeRandomCategoryEntityList(10)
        stubCategoryRemoteResponse(response)
        val argument :Int = randomBuild()
        val data = categoriesRepositoryImpl.getCategories(argument).first()
        assertThat(categoryEntityMapper.mapFromEntityList(response)).isEqualTo(data)
    }

    @Test(expected = SocketTimeoutException::class)
    fun `check that calling getCategories with returns an error when one occurs in the remote call`() = runBlocking {
        stubCategoryRemoteError(SocketTimeoutException())
        val argument :Int = randomBuild()
        val error = categoriesRepositoryImpl.getCategories(argument).first()
        assertThat(error).isInstanceOf(SocketTimeoutException::class.java)
    }

    private fun stubCategoryRemoteError(error: Exception) {
        coEvery { categoriesRemote.getCategories(any()) } throws  error
    }

    private fun stubCategoryRemoteResponse(categories: List<CategoryEntity>) {
        coEvery { categoriesRemote.getCategories(any()) } returns categories
    }
}