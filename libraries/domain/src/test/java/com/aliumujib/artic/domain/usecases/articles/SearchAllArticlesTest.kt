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
package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.exceptions.EmptyQueryException
import com.aliumujib.artic.domain.exceptions.NoParamsException
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
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
import java.net.SocketTimeoutException


class SearchAllArticlesTest {

    private lateinit var searchArticles: SearchAllArticles
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        searchArticles = SearchAllArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling searchArticles returns data`() = runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(list)
        })
        val query = ArticleDataFactory.makeProject().title
        val page: Int = randomBuild()
        val count: Int = randomBuild()
        val params = SearchAllArticles.Params.make(query, page, count)
        val result = searchArticles.build(params).first()
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            articlesRepository.searchArticles(query, page, count)
        }
    }

    @Test(expected = NoParamsException::class)
    fun `confirm that using searchArticles without params throws an exception`() = runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(list)
        })
        searchArticles.build().first()
        coVerify(exactly = 0) {
            articlesRepository.searchArticles(any(), any(), any())
        }
    }

    @Test(expected = EmptyQueryException::class)
    fun `confirm that using searchArticles with an emptyString throws an exception`() = runBlockingTest {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(projects)
        })
        val params = SearchAllArticles.Params.make("", 0, randomBuild())
        searchArticles.build(params).first()
        coVerify(exactly = 0) {
            articlesRepository.searchArticles(any(), any(), any())
        }
    }

    private fun stubSearchArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.searchArticles(any(), any(), any())
        } returns flow
    }

}
