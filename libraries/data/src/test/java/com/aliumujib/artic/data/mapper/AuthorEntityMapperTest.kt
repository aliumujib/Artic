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

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.domain.models.Author
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class AuthorEntityMapperTest {


    private var authorEntityMapper: AuthorEntityMapper = AuthorEntityMapper()


    @Before
    fun setup() {

    }


    @Test
    fun `check that AuthorEntity mapped from Author maps correct info`() {
        val authorEntity = DummyDataFactory.makeRandomAuthorEntity()
        val author = authorEntityMapper.mapFromEntity(authorEntity)
        assertEqualValues(authorEntity, author)
    }


    @Test
    fun `check that Author mapped to AuthorEntity maps correct info`() {
        val author = DummyDataFactory.makeRandomAuthor()
        val authorEntity = authorEntityMapper.mapToEntity(author)
        assertEqualValues(authorEntity, author)
    }


    private fun assertEqualValues(entity: AuthorEntity, domain: Author) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.slug, domain.slug)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.first_name, domain.first_name)
        assertEquals(entity.last_name, domain.last_name)
        assertEquals(entity.nickname, domain.nickname)
        assertEquals(entity.url, domain.url)
        assertEquals(entity.description, domain.description)
    }

}
