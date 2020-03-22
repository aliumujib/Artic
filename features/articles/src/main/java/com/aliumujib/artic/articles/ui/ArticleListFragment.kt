package com.aliumujib.artic.articles.ui

import android.content.Context
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.articles.presentation.ArticleListViewState
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import com.aliumujib.artic.views.basearticlelist.BaseArticleListFragment
import com.aliumujib.artic.views.basearticlelist.adapter.ArticleClickListener
import com.aliumujib.artic.views.ext.isLastItemDisplaying
import com.aliumujib.artic.views.ext.nonNullObserve
import com.aliumujib.artic.views.models.ArticleUIModel
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
import reactivecircus.flowbinding.swiperefreshlayout.refreshes
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ArticleListFragment : BaseArticleListFragment(), MVIView<ArticleListIntent, ArticleListViewState>,
    ArticleClickListener {

    @Inject
    lateinit var viewModel: ArticleListViewModel

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private var _viewModeBtn: MenuItem? = null


    private val _loadInitialIntent = BroadcastChannel<ArticleListIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    private val _listActionIntents = BroadcastChannel<ArticleListIntent>(1)
    private val listActionIntents = _listActionIntents.asFlow()


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
        _loadInitialIntent.offer(ArticleListIntent.LoadArticleListIntent)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStates()
    }

    private fun observeStates() {
        nonNullObserve(viewModel.states(), ::render)
    }


    private fun loadMoreIntent(): Flow<ArticleListIntent> {
        return binding.articles.scrollStateChanges()
            .filterNot { _ -> articlesAdapter.isLoadingNextPage() } //only runs when adapter is NOT loading
            .filter { event -> event == RecyclerView.SCROLL_STATE_IDLE }
            .filter { _ -> binding.articles.isLastItemDisplaying() }
            .map {
                ArticleListIntent.FetchMoreArticleListIntent
            }
    }

    private fun refreshIntent(): Flow<ArticleListIntent> {
        return binding.swipeContainer.refreshes().map { ArticleListIntent.RefreshArticleListIntent }
    }

    private fun loadInitialIntent(): Flow<ArticleListIntent> {
        return loadInitialIntent.filter { articlesAdapter.isEmpty() } //only runs when adapter is empty
    }

    private fun listActionIntents(): Flow<ArticleListIntent> {
        return listActionIntents
    }

    override fun render(state: ArticleListViewState) {
        when {
            !state.isLoading && (state.error == null) -> presentSuccessState(articleUIModelMapper.mapToUIList(state.data), state.isGrid)
            state.error != null -> presentErrorState(state.error, state.isLoadingMore, state.data.isEmpty())
            state.isLoading -> presentLoadingState(state.isLoadingMore)
        }
    }


    override fun setListMode(isGrid: Boolean) {
        _viewModeBtn?.let {
            if (isGrid && binding.articles.layoutManager !is LinearLayoutManager) {
                it.icon = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_list_to_grid)
                (it.icon as Animatable).start()
            } else if (!isGrid && binding.articles.layoutManager !is StaggeredGridLayoutManager) {
                it.icon = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_grid_to_list)
                (it.icon as Animatable).start()
            }
            //animated first so the layoutmanager doesn't changed and invalid the check above
            super.setListMode(isGrid)
        }
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    private fun initDependencyInjection() {
        DaggerArticleListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .articleListModule(ArticleListModule(this))
            .build()
            .inject(this)
    }

    override fun intents(): Flow<ArticleListIntent> {
        return merge(loadMoreIntent(), loadInitialIntent(), listActionIntents(), refreshIntent())
    }

    override fun onArticleClicked(articleUIModel: ArticleUIModel) {
        findNavController().navigate(ArticleListFragmentDirections.actionArticleListFragmentToNavDetails(articleUIModel))
    }

    override fun onBookmarkBtnClicked(articleUIModel: ArticleUIModel, isBookmarked: Boolean) {
        _listActionIntents.offer(ArticleListIntent.SetArticleBookmarkStatusIntent(articleUIModelMapper.mapFromUI(articleUIModel), isBookmarked))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_home, menu)
        _viewModeBtn = menu.findItem(R.id.action_switch_mode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search ->  // do stuff
                return true
            R.id.action_switch_mode ->  // do more stuff
                if (!(item.icon as Animatable).isRunning) {
                    _listActionIntents.offer(
                        ArticleListIntent.SwitchArticleListViewModeIntent(binding.articles.layoutManager is StaggeredGridLayoutManager)
                    )
                    return true
                }
        }
        return false
    }

}
