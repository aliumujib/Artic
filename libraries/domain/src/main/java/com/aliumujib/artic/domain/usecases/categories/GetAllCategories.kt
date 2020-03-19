package com.aliumujib.artic.domain.usecases.categories


import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategories @Inject constructor(
    private val categoriesRepository: ICategoriesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<GetAllCategories.Params, List<Category>>(postExecutionThread) {

    override fun build(params: Params?): Flow<List<Category>> {
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