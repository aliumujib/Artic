package com.aliumujib.artic.domain.repositories.categories

import com.aliumujib.artic.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface ICategoriesRepository {

    fun getCategories(page: Int): Flow<List<Category>>

}