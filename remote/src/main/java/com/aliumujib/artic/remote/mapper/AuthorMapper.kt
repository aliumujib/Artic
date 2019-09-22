package com.aliumujib.artic.remote.mapper

import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.remote.models.Author
import javax.inject.Inject

class AuthorMapper @Inject constructor(): RemoteModelMapper<Author, AuthorEntity> {

    override fun mapFromModel(model: Author): AuthorEntity {
        return AuthorEntity(
            model.id,
            model.slug,
            model.name,
            model.first_name,
            model.last_name,
            model.nickname,
            model.url,
            model.description
        )
    }

}