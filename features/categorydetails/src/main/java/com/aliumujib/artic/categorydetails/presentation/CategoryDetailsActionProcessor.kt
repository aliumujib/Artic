package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.categories.GetArticlesForCategory
import com.aliumujib.artic.domain.usecases.settings.FetchViewModeSettings
import com.aliumujib.artic.domain.usecases.settings.UpdateViewModeSettings
import com.aliumujib.artic.views.ext.ofType
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class CategoryDetailsActionProcessor @Inject constructor(
    private val getArticlesForCategory: GetArticlesForCategory,
    private val setArticleBookmarkStatus: SetArticleBookmarkStatus,
    private val fetchViewModeSettings: FetchViewModeSettings
) {

    fun actionToResultTransformer(action: CategoryDetailsAction): Flow<CategoryDetailsResult> {
        return when (action) {
            is CategoryDetailsAction.LoadCategoryArticlesAction -> {
                loadArticleListResult(flowOf(action))
            }
            is CategoryDetailsAction.FetchMoreArticlesForCategoryAction -> {
                loadAnotherArticleListResult(flowOf(action))
            }
            is CategoryDetailsAction.SetArticleBookmarkStatusAction -> {
                bookmarkArticleResult(flowOf(action))
            }
        }
    }

    private fun loadArticleListResult(actionsFlow: Flow<CategoryDetailsAction.LoadCategoryArticlesAction>): Flow<CategoryDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            getArticlesForCategory.build(GetArticlesForCategory.Params.make(action.page, action.categoryId))
                .combine(flow { emit(fetchViewModeSettings.invoke()) })
                { list: List<Article>, isGrid: Boolean ->
                    CategoryDetailsResult.LoadCategoryArticlesResults.Success(list, isGrid)
                }.map { result ->
                    result as CategoryDetailsResult
                }
                .onStart { emit(CategoryDetailsResult.LoadCategoryArticlesResults.Loading) }
                .catch {
                    Timber.e(it)
                    emit(CategoryDetailsResult.LoadCategoryArticlesResults.Error(it))
                }
        }
    }

    private fun loadAnotherArticleListResult(actionsFlow: Flow<CategoryDetailsAction.FetchMoreArticlesForCategoryAction>): Flow<CategoryDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            getArticlesForCategory.build(GetArticlesForCategory.Params.make(action.page, action.categoryId))
                .map { articles ->
                    CategoryDetailsResult.FetchMoreArticlesForCategoryResults.Success(data = articles) as CategoryDetailsResult
                }
                .onStart { emit(CategoryDetailsResult.FetchMoreArticlesForCategoryResults.Loading) }
                .catch {
                    Timber.e(it)
                    emit(CategoryDetailsResult.FetchMoreArticlesForCategoryResults.Error(it))
                }
        }
    }


    private fun bookmarkArticleResult(actionsFlow: Flow<CategoryDetailsAction.SetArticleBookmarkStatusAction>): Flow<CategoryDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params.make(action.article, action.isBookmarked)))
            }.map {
                CategoryDetailsResult.SetBookmarkStatusResults.Success(it!!) as CategoryDetailsResult
            }.catch {
                Timber.e(it)
                emit(CategoryDetailsResult.SetBookmarkStatusResults.Error(it))
            }
        }
    }

}