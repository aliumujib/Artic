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
package com.aliumujib.artic.cache.models.mappers

import com.aliumujib.artic.cache.models.CategoryCacheModel
import com.aliumujib.artic.data.model.CategoryEntity
import javax.inject.Inject

class CategoryCacheModelMapper @Inject constructor() :
    CacheModelMapper<CategoryCacheModel, CategoryEntity> {

    override fun mapToEntity(model: CategoryCacheModel): CategoryEntity {
        return CategoryEntity(
            model.id,
            model.slug,
            model.title,
            model.description,
            model.parent,
            model.postCount
        )
    }

    override fun mapToModel(entity: CategoryEntity): CategoryCacheModel {
        return CategoryCacheModel(
            entity.id,
            entity.slug,
            entity.title,
            entity.description,
            entity.parent,
            entity.postCount
        )
    }

}
