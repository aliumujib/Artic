package com.aliumujib.artic.categorydetails.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.ui.ArticleListFragmentDirections
import com.aliumujib.artic.categorydetails.di.CategoryDetailsModule
import com.aliumujib.artic.categorydetails.di.DaggerCategoryDetailsComponent
import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsIntent
import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsViewModel
import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsViewState
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.basearticlelist.BaseArticleListFragment
import com.aliumujib.artic.views.ext.isLastItemDisplaying
import com.aliumujib.artic.views.ext.nonNullObserve
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.models.CategoryUIModel
import com.aliumujib.artic.views.mvi.MVIView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.take
import reactivecircus.flowbinding.recyclerview.scrollStateChanges
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CategoryDetailsFragment : BaseArticleListFragment(), MVIView<CategoryDetailsIntent, CategoryDetailsViewState> {

    @Inject
    lateinit var viewModel: CategoryDetailsViewModel

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private val _loadInitialIntent = BroadcastChannel<CategoryDetailsIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    private val _listActionIntents = BroadcastChannel<CategoryDetailsIntent>(1)
    private val listActionIntents = _listActionIntents.asFlow()

    private val categoryArgs by navArgs<CategoryDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.processActions()
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.processIntent(intents())
        _loadInitialIntent.offer(CategoryDetailsIntent.LoadCategoryArticlesIntent(categoryArgs.category.id))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStates()
    }

    private fun observeStates() {
        nonNullObserve(viewModel.states(), ::render)
    }

    private fun loadMoreIntent(): Flow<CategoryDetailsIntent> {
        return binding.articles.scrollStateChanges()
            .filterNot { _ -> articlesAdapter.isLoadingNextPage() } //only runs when adapter is NOT loading
            .filter { event -> event == RecyclerView.SCROLL_STATE_IDLE }
            .filter { _ -> binding.articles.isLastItemDisplaying() }
            .map {
                CategoryDetailsIntent.FetchMoreArticlesForCategoryIntent(categoryArgs.category.id)
            }
    }

    private fun loadInitialIntent(): Flow<CategoryDetailsIntent> {
        return loadInitialIntent.filter { articlesAdapter.isEmpty() } //only runs when adapter is empty
    }

    private fun listActionIntents(): Flow<CategoryDetailsIntent> {
        return listActionIntents
    }

    override fun render(state: CategoryDetailsViewState) {
        when {
            !state.isLoading && (state.error == null) -> presentSuccessState(articleUIModelMapper.mapToUIList(state.data), state.isGrid)
            state.error != null -> presentErrorState(state.error, state.isLoadingMore, state.data.isEmpty())
            state.isLoading -> presentLoadingState(state.isLoadingMore)
        }
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    private fun initDependencyInjection() {
        DaggerCategoryDetailsComponent
            .builder()
            .coreComponent(ApplicationClass.coreComponent(requireContext()))
            .categoryDetailsModule(CategoryDetailsModule(this))
            .build()
            .inject(this)
    }


    override fun intents(): Flow<CategoryDetailsIntent> {
        return merge(loadMoreIntent(), loadInitialIntent(), listActionIntents())
    }

    override fun onBookmarkBtnClicked(articleUIModel: ArticleUIModel, isBookmarked: Boolean) {
        _listActionIntents.offer(CategoryDetailsIntent.SetArticleBookmarkStatusIntent(articleUIModelMapper.mapFromUI(articleUIModel), isBookmarked))
    }

    override fun onArticleClicked(articleUIModel: ArticleUIModel) {
        findNavController().navigate(CategoryDetailsFragmentDirections.actionCategoryDetailsFragmentToNavDetails(articleUIModel))
    }

}