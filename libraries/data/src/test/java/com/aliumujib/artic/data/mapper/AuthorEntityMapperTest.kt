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