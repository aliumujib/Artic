package com.aliumujib.artic.cache.models.mappers

import com.aliumujib.artic.cache.models.CategoryCacheModel
import com.aliumujib.artic.data.model.CategoryEntity
import javax.inject.Inject

class CategoryCacheModelMapper @Inject constructor() :
    CacheModelMapper<CategoryCacheModel, CategoryEntity> {

    override fun mapToEntity(model: CategoryCacheModel): CategoryEntity {
        return CategoryEntity(
            model.id,
            model.slug,
            model.title,
            model.description,
            model.parent,
            model.postCount
        )
    }

    override fun mapToModel(entity: CategoryEntity): CategoryCacheModel {
        return CategoryCacheModel(
            entity.id,
            entity.slug,
            entity.title,
            entity.description,
            entity.parent,
            entity.postCount
        )
    }

}