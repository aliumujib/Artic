package com.aliumujib.artic.articles.models

class AuthorUIModelMapper: UIModelMapper<Author, AuthorUIModel>() {

    override fun mapToUI(entity: Author): AuthorUIModel {
        return AuthorUIModel()
    }

    override fun mapFromUI(domain: AuthorUIModel): Author {
        return Author()
    }

}