package com.aliumujib.artic.cache.models.mappers

import com.aliumujib.artic.cache.models.AuthorCacheModel
import com.aliumujib.artic.data.model.AuthorEntity
import javax.inject.Inject

class AuthorCacheModelMapper @Inject constructor() : CacheModelMapper<AuthorCacheModel, AuthorEntity> {

    override fun mapToModel(entity: AuthorEntity): AuthorCacheModel {
        return AuthorCacheModel(
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

    override fun mapToEntity(model: AuthorCacheModel): AuthorEntity {
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