package com.aliumujib.artic.articles.models

import com.aliumujib.artic.domain.models.Author
import com.aliumujib.artic.views.models.AuthorUIModel
import com.aliumujib.artic.views.mappers.UIModelMapper
import javax.inject.Inject

class AuthorUIModelMapper @Inject constructor() : UIModelMapper<Author, AuthorUIModel>() {

    override fun mapToUI(entity: Author): AuthorUIModel {
        return AuthorUIModel(
            entity.id, entity.slug, entity.name, entity.first_name, entity.last_name,
            entity.nickname, entity.url, entity.description
        )
    }

    override fun mapFromUI(domain: AuthorUIModel): Author {
        return Author(
            domain.id, domain.slug, domain.name, domain.first_name, domain.last_name,
            domain.nickname, domain.url, domain.description
        )
    }

}