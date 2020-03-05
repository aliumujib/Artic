package com.aliumujib.artic.articles.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aliumujib.artic.articles.databinding.ArticleListFragmentBinding
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.list.adapter.ArticleListAdapter
import com.aliumujib.artic.articles.list.adapter.InfiniteScrollListener
import com.aliumujib.artic.articles.models.ArticleUIModel
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.articles.presentation.ArticleListViewState
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import com.aliumujib.artic.views.ext.*
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.recyclerview.SpacingItemDecoration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.recyclerview.scrollStateChanges
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ArticleListFragment : Fragment(), MVIView<ArticleListIntent, ArticleListViewState> {


    @Inject
    lateinit var viewModel: ArticleListViewModel

    @Inject
    lateinit var articlesAdapter: ArticleListAdapter

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private var _binding: ArticleListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.processActions()
        viewModel.processIntent(ArticleListIntent.LoadArticleListIntent(true))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()

        viewModel.statesFlow
            .onEach {
                render(it)
            }.launchIn(lifecycleScope)
    }

    private fun initializeViews() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.articles.apply {
            removeAllDecorations()
            addItemDecoration(
                SpacingItemDecoration(
                    context.dpToPx(16),
                    context.dpToPx(16), doubleFirstItemLeftMargin = false, isVertical = true
                )
            )
            layoutManager = staggeredGridLayoutManager
            adapter = articlesAdapter
        }


        binding.articles.scrollStateChanges()
            .filter { _ -> !articlesAdapter.isLoadingNextPage() }
            .filter { event -> event == RecyclerView.SCROLL_STATE_IDLE }
            .filter { _ -> binding.articles.isLastItemDisplaying() }
            .onEach {
                viewModel.processIntent(ArticleListIntent.FetchMoreArticleListIntent)
            }.launchIn(lifecycleScope)
    }


    override fun render(state: ArticleListViewState) {
        when (state) {
            is ArticleListViewState.Success -> presentSuccessState(
                articleUIModelMapper.mapToUIList(
                    state.countries
                )
            )
            is ArticleListViewState.Error -> presentErrorState(
                state.throwable,
                state.isLoadingMoreData
            )
            is ArticleListViewState.Loading -> presentLoadingState(
                state.isGrid,
                state.isLoadingMore
            )
            is ArticleListViewState.Idle -> {

            }
        }
    }

    private fun presentSuccessState(data: List<ArticleUIModel>) {
        binding.shimmerViewContainer.stopShimmerAnimation()
        articlesAdapter.setListState(ArticleListAdapter.ListState.Idle)
        binding.gridLoading.hide()
        binding.listLoading.hide()

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

    private fun presentErrorState(error: Throwable, isLoadingMoreData: Boolean) {
        binding.emptyView.hide()
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.hide()
        if (isLoadingMoreData) {
            binding.articles.show()
            articlesAdapter.setListState(ArticleListAdapter.ListState.Error(error))
        } else {
            binding.articles.hide()
            binding.errorView.show()
        }
        error.message?.let {
            binding.errorView.setErrorViewText(it)
        }
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    private fun presentLoadingState(isGrid: Boolean, isLoadingMoreData: Boolean) {
        binding.shimmerViewContainer.show()
        binding.shimmerViewContainer.startShimmerAnimation()
        when {
            isLoadingMoreData -> {
                binding.articles.show()
                articlesAdapter.setListState(ArticleListAdapter.ListState.Loading)
                return
            }
            isGrid -> {
                binding.articles.hide()
                binding.listLoading.hide()
                binding.gridLoading.show()
            }
            else -> {
                binding.articles.hide()
                binding.listLoading.show()
                binding.gridLoading.hide()
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


}
