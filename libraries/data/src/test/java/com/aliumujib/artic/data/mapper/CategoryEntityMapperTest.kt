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
import konveyor.base.randomBuild
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CategoryEntityMapperTest {

    private var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()

    @Before
    fun setup() {

    }


    @Test
    fun `check that CategoryEntity mapped to domain Category contains correct info`() {
        val categoryEntity: CategoryEntity = randomBuild()
        val category = categoryEntityMapper.mapFromEntity(categoryEntity)

        assertEqualData(categoryEntity, category)
    }


    @Test
    fun `check that Category mapped to data CategoryEntity contains correct info`() {
        val category: Category = randomBuild()
        val categoryEntity = categoryEntityMapper.mapToEntity(category)

        assertEqualData(categoryEntity, category)
    }

    private fun assertEqualData(entity: CategoryEntity, domain: Category) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.slug, domain.slug)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.description, domain.description)
        assertEquals(entity.parent, domain.parent)
        assertEquals(entity.postCount, domain.postCount)
    }

}
