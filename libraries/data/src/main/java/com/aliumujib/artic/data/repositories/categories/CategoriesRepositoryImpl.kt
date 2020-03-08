package com.aliumujib.artic.data.repositories.categories

import com.aliumujib.artic.data.mapper.CategoryEntityMapper
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesRemote: ICategoriesRemote,
    private val categoryEntityMapper: CategoryEntityMapper
) : ICategoriesRepository {

    override fun getCategories(page: Int): Flow<List<Category>> {
        return flow {
            emit(categoriesRemote.getCategories(page).map {
                categoryEntityMapper.mapFromEntity(it)
            })
        }
    }

}