package com.aliumujib.artic.domain.repositories.categories

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Category
import io.reactivex.Completable
import io.reactivex.Observable


interface ICategoriesRepository {

    fun getCategories(page: Int): Observable<List<Category>>

}