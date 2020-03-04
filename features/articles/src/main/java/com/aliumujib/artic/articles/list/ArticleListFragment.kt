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
import com.aliumujib.artic.articles.models.ArticleUIModel
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.articles.presentation.ArticleListViewState
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import com.aliumujib.artic.views.ext.dpToPx
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.removeAllDecorations
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.recyclerview.SpacingItemDecoration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
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

        binding.articles.apply {
            removeAllDecorations()
            addItemDecoration(
                SpacingItemDecoration(
                    context.dpToPx(16),
                    context.dpToPx(16), doubleFirstItemLeftMargin = false, isVertical = true
                )
            )
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = articlesAdapter
        }

        lifecycleScope.launchWhenResumed {
            viewModel.statesFlow.collect {
                articlesAdapter.submitList(articleUIModelMapper.mapToUIList(it.data))
            }
        }
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
        if (isLoadingMoreData) {
            //TODO show RV here
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
        binding.shimmerViewContainer.startShimmerAnimation()
        when {
            isLoadingMoreData -> {
                //TODO show loading more view in adapter
                return
            }
            isGrid -> {
                binding.gridLoading.show()
            }
            else -> {
                binding.listLoading.show()
            }
        }
        //emptyView.visible = false
        //errorView.visible = false
        //binding.articles.visible = false
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
