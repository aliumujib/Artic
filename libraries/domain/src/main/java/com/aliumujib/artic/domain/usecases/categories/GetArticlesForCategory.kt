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
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesForCategory @Inject constructor(
    private val articlesRepository: IArticlesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetArticlesForCategory.Params, List<Article>>(postExecutionThread) {

    override fun build(params: Params?): Flow<List<Article>> {
        if (params == null) {
            throw NoParamsException()
        }
        return articlesRepository.getArticlesByCategoryId(params.categoryId, params.page, params.count)
    }

    data class Params constructor(val page: Int, val categoryId: Int, val count: Int) {
        companion object {
            fun make(page: Int, categoryId: Int, count: Int): Params {
                return Params(page, categoryId, count)
            }
        }
    }

}
