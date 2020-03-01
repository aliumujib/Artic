package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.domain.models.Category
import javax.inject.Inject

open class CategoryEntityMapper @Inject constructor(): EntityMapper<CategoryEntity, Category>() {


    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(entity.id, entity.slug, entity.title, entity.description, entity.parent, entity.postCount)
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(domain.id, domain.slug, domain.title, domain.description, domain.parent, domain.postCount)
    }

}