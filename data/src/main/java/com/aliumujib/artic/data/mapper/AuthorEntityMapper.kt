package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.domain.models.Author
import javax.inject.Inject

open class AuthorEntityMapper @Inject constructor(): EntityMapper<AuthorEntity, Author>() {

    override fun mapFromEntity(entity: AuthorEntity): Author {
        return Author(
            entity.id,
            entity.slug,
            entity.name,
            entity.first_name,
            entity.last_name,
            entity.nickname,
            entity.url,
            entity.description
        )
    }

    override fun mapToEntity(domain: Author): AuthorEntity {
        return AuthorEntity(
            domain.id,
            domain.slug,
            domain.name,
            domain.first_name,
            domain.last_name,
            domain.nickname,
            domain.url,
            domain.description
        )
    }

}