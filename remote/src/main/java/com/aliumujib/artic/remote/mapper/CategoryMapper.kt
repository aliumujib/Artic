package com.aliumujib.artic.remote.mapper

import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.remote.models.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() : RemoteModelMapper<Category, CategoryEntity> {

    override fun mapFromModel(model: Category): CategoryEntity {
        return CategoryEntity(model.id, model.slug, model.title, model.description, model.parent, model.post_count)
    }

}
