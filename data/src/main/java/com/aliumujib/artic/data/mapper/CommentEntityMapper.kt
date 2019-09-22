package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.CommentEntity
import com.aliumujib.artic.domain.models.Comment
import javax.inject.Inject

open class CommentEntityMapper @Inject constructor() : EntityMapper<CommentEntity, Comment>() {

    override fun mapFromEntity(entity: CommentEntity): Comment {
        return Comment(entity.id, entity.name, entity.url, entity.date, entity.content, entity.parentId)
    }

    override fun mapToEntity(domain: Comment): CommentEntity {
        return CommentEntity(domain.id, domain.name, domain.url, domain.date, domain.content, domain.parentId)
    }

}