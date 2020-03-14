package com.aliumujib.artic.articles.models

import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.views.models.CategoryUIModel
import com.aliumujib.artic.views.models.mappers.UIModelMapper
import javax.inject.Inject

class CategoryUIModelMapper @Inject constructor() : UIModelMapper<Category, CategoryUIModel>() {

    override fun mapToUI(entity: Category): CategoryUIModel {
        return CategoryUIModel(
            entity.id,
            entity.slug,
            entity.title,
            entity.description,
            entity.parent,
            entity.postCount
        )
    }

    override fun mapFromUI(domain: CategoryUIModel): Category {
        return Category(
            domain.id,
            domain.slug,
            domain.title,
            domain.description,
            domain.parent,
            domain.postCount
        )
    }


}