package com.aliumujib.artic.data.repositories.categories.remote

import com.aliumujib.artic.data.model.CategoryEntity

interface ICategoriesRemote {

    suspend fun getCategories(page: Int): List<CategoryEntity>

}