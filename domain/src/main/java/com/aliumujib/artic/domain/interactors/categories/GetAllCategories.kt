package com.aliumujib.artic.domain.interactors.categories


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.interactors.ObservableUseCase
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllCategories @Inject constructor(
    private val categoriesRepository: ICategoriesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<GetAllCategories.Params, List<Category>>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Category>> {
        if (params == null) {
            throw IllegalStateException("Your params can;t be null for this use case")
        }
        return categoriesRepository.getCategories(params.page)
    }

    data class Params constructor(val page: Int) {
        companion object {
            fun make(page: Int): Params {
                return Params(page)
            }
        }
    }
}