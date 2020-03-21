package com.aliumujib.artic.articles.ui

import android.content.Context
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.databinding.ArticleListFragmentBinding
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.articles.presentation.ArticleListViewState
import com.aliumujib.artic.articles.ui.adapter.ArticleClickListener
import com.aliumujib.artic.articles.ui.adapter.ArticleListAdapter
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import com.aliumujib.artic.views.ext.dpToPx
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.isLastItemDisplaying
import com.aliumujib.artic.views.ext.nonNullObserve
import com.aliumujib.artic.views.ext.removeAllDecorations
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.recyclerview.GridSpacingItemDecoration
import com.aliumujib.artic.views.recyclerview.ListSpacingItemDecorator
import com.aliumujib.artic.views.recyclerview.ListState
import com.eyowo.android.core.utils.autoDispose
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
class ArticleListFragment : Fragment(), MVIView<ArticleListIntent, ArticleListViewState>,
    ArticleClickListener {

    @Inject
    lateinit var viewModel: ArticleListViewModel

    @Inject
    lateinit var articlesAdapter: ArticleListAdapter

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private var _viewModeBtn: MenuItem? = null

    private var _binding: ArticleListFragmentBinding by autoDispose()
    private val binding get() = _binding

    private val _loadInitialIntent = BroadcastChannel<ArticleListIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    private val _listActionIntents = BroadcastChannel<ArticleListIntent>(1)
    private val listActionIntents = _listActionIntents.asFlow()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

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

        initializeViews()

        observeStates()
    }

    private fun observeStates() {
        nonNullObserve(viewModel.states(), ::render)
    }


    private fun provideStaggeredGridLayoutManager(): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    private fun provideListLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun initializeViews() {
        binding.articles.apply {
            removeAllDecorations()
            addItemDecoration(
                GridSpacingItemDecoration(
                    2,
                    context.dpToPx(16),
                    true
                )
            )
            layoutManager = provideStaggeredGridLayoutManager()
            adapter = articlesAdapter
        }
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
        return binding.swipeContainer.refreshes()
            .map {
                ArticleListIntent.RefreshArticleListIntent
            }
    }

    private fun loadInitialIntent(): Flow<ArticleListIntent> {
        return loadInitialIntent.filter { articlesAdapter.isEmpty() } //only runs when adapter is empty
    }

    private fun listActionIntents(): Flow<ArticleListIntent> {
        return listActionIntents
    }

    override fun render(state: ArticleListViewState) {
        when {
            !state.isLoading && (state.error == null) -> presentSuccessState(
                articleUIModelMapper.mapToUIList(
                    state.data
                ), state.isGrid
            )
            state.error != null -> presentErrorState(
                state.error,
                state.isLoadingMore,
                state.data.isEmpty()
            )
            state.isLoading -> presentLoadingState(state.isLoadingMore)
        }
    }


    private fun changeListMode(isGrid: Boolean) {
        _viewModeBtn?.let {
            if (isGrid && binding.articles.layoutManager !is LinearLayoutManager) {
                it.icon = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_list_to_grid)
                binding.articles.apply {
                    removeAllDecorations()
                    addItemDecoration(ListSpacingItemDecorator(context.dpToPx(32), context.dpToPx(16)))
                    layoutManager = provideListLayoutManager()
                }
                articlesAdapter.setLayout(ArticleListAdapter.LAYOUT.LIST)
                (it.icon as Animatable).start()
            } else if (isGrid.not() && binding.articles.layoutManager !is StaggeredGridLayoutManager) {
                it.icon = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_grid_to_list)
                binding.articles.apply {
                    removeAllDecorations()
                    addItemDecoration(GridSpacingItemDecoration(2, context.dpToPx(16), true))
                    layoutManager = provideStaggeredGridLayoutManager()
                }
                articlesAdapter.setLayout(ArticleListAdapter.LAYOUT.GRID)
                (it.icon as Animatable).start()
            }
        }
    }

    private fun presentSuccessState(data: List<ArticleUIModel>, grid: Boolean) {
        changeListMode(grid)
        articlesAdapter.setListState(ListState.Idle)
        binding.loading.hide()
        binding.swipeContainer.isRefreshing = false
        if (data.isNotEmpty()) {
            binding.emptyView.hide()
            binding.errorView.hide()
            binding.articles.show()
        } else {
            binding.emptyView.show()
            binding.errorView.hide()
            binding.articles.hide()
        }

        articlesAdapter.submitList(data)
    }

    private fun presentErrorState(
        error: Throwable,
        isLoadingMoreData: Boolean,
        isEmptyList: Boolean
    ) {
        binding.swipeContainer.isRefreshing = false
        binding.emptyView.hide()
        binding.loading.hide()
        if (isLoadingMoreData) {
            binding.articles.show()
            articlesAdapter.setListState(ListState.Error(error))
        } else if (isEmptyList && isLoadingMoreData.not()) {
            binding.articles.hide()
            binding.errorView.show()
        }
        error.message?.let {
            binding.errorView.setErrorViewText(it)
        }
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    private fun presentLoadingState(isLoadingMoreData: Boolean) {
        when {
            isLoadingMoreData -> {
                binding.articles.show()
                articlesAdapter.setListState(ListState.Loading)
            }
            else -> {
                binding.articles.hide()
                binding.loading.show()
            }
        }
        binding.emptyView.hide()
        binding.errorView.hide()
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
        _listActionIntents.offer(
            ArticleListIntent.SetArticleBookmarkStatusIntent(
                articleUIModelMapper.mapFromUI(articleUIModel),
                isBookmarked
            )
        )
    }

    override fun onShareBtnClicked(articleUIModel: ArticleUIModel) {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setText(articleUIModel.url)
            .intent
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
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
