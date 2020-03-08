package com.aliumujib.artic.remote.impl

import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.mapper.CategoryMapper
import javax.inject.Inject

class CategoriesRemote @Inject constructor(
    var wordPressAPI: WordPressAPI,
    var categoryMapper: CategoryMapper
) : ICategoriesRemote {

    override suspend fun getCategories(page: Int): List<CategoryEntity> {
        return wordPressAPI.fetchAllCategories().categories.map {
            categoryMapper.mapFromModel(it)
        }
    }

    companion object {
        private const val PER_PAGE = 5
    }

}