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
package com.aliumujib.artic.remote.impl

import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.mapper.CategoryMapper
import javax.inject.Inject

class CategoriesRemote @Inject constructor(
    var wordPressAPI: WordPressAPI,
    var categoryMapper: CategoryMapper
) : ICategoriesRemote {

    override suspend fun getCategories(page: Int, count:Int): List<CategoryEntity> {
        return wordPressAPI.fetchAllCategories().categories.map {
            categoryMapper.mapFromModel(it)
        }
    }

    companion object {
        private const val PER_PAGE = 5
    }

}
