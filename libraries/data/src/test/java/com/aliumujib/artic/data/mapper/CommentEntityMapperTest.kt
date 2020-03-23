package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.CommentEntity
import com.aliumujib.artic.domain.models.Comment
import konveyor.base.randomBuild
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CommentEntityMapperTest {


    private var commentEntityMapper: CommentEntityMapper = CommentEntityMapper()

    @Before
    fun setup() {
        commentEntityMapper = CommentEntityMapper()
    }


    @Test
    fun `check that CommentEntity mapped to domain Comment contains correct info`() {
        var commentEntity: CommentEntity = randomBuild()
        var comment = commentEntityMapper.mapFromEntity(commentEntity)
        assertEqualData(commentEntity, comment)
    }


    @Test
    fun `check that Comment mapped to data CommentEntity contains correct info`() {
        var comment: Comment = randomBuild()
        var commentEntity = commentEntityMapper.mapToEntity(comment)
        assertEqualData(commentEntity, comment)
    }

    private fun assertEqualData(entity: CommentEntity, domain: Comment) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.content, domain.content)
        assertEquals(entity.parentId, domain.parentId)
    }

}