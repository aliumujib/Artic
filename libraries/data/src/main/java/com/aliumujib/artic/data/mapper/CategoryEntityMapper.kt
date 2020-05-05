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
package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.domain.models.Category
import javax.inject.Inject

open class CategoryEntityMapper @Inject constructor(): EntityMapper<CategoryEntity, Category>() {


    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(entity.id, entity.slug, entity.title, entity.description, entity.parent, entity.postCount)
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(domain.id, domain.slug, domain.title, domain.description, domain.parent, domain.postCount)
    }

}
