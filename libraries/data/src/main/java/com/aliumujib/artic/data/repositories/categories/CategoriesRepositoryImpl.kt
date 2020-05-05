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

import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesRemote: ICategoriesRemote,
    private val categoryEntityMapper: CategoryEntityMapper
) : ICategoriesRepository {

    override fun getCategories(page: Int, count: Int): Flow<List<Category>> {
        return flow {
            emit(categoriesRemote.getCategories(page, count).map {
                categoryEntityMapper.mapFromEntity(it)
            })
        }
    }

}
