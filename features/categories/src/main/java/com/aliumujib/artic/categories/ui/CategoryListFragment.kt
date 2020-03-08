package com.aliumujib.artic.categories.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aliumujib.artic.articles.databinding.ArticleListFragmentBinding
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.models.ArticleUIModel
import com.aliumujib.artic.articles.models.CategoryUIModel
import com.aliumujib.artic.articles.models.CategoryUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.ui.adapter.ArticleListAdapter
import com.aliumujib.artic.categories.R
import com.aliumujib.artic.categories.databinding.FragmentCategoriesBinding
import com.aliumujib.artic.categories.di.CategoryListModule
import com.aliumujib.artic.categories.di.DaggerCategoryListComponent
import com.aliumujib.artic.categories.presentation.CategoryListIntent
import com.aliumujib.artic.categories.presentation.CategoryListViewModel
import com.aliumujib.artic.categories.presentation.CategoryListViewState
import com.aliumujib.artic.categories.ui.adapter.CategoryListAdapter
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.ext.dpToPx
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.removeAllDecorations
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.recyclerview.SpacingItemDecoration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CategoryListFragment : Fragment(), MVIView<CategoryListIntent, CategoryListViewState> {

    @Inject
    lateinit var categoryListAdapter: CategoryListAdapter

    @Inject
    lateinit var categoryListViewModel: CategoryListViewModel

    @Inject
    lateinit var categoryUIModelMapper: CategoryUIModelMapper

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val _loadInitialIntent = ConflatedBroadcastChannel<CategoryListIntent>()
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryListViewModel.processIntent(intents())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryListViewModel.processActions()
        _loadInitialIntent.offer(CategoryListIntent.LoadCategoriesListIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        categoryListViewModel.states().onEach {
            render(it)
        }.launchIn(lifecycleScope)

    }

    private fun initializeViews() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.categories.apply {
            removeAllDecorations()
            addItemDecoration(
                SpacingItemDecoration(
                    context.dpToPx(16),
                    context.dpToPx(16)
                )
            )
            layoutManager = linearLayoutManager
            adapter = categoryListAdapter
        }
    }


    private fun initDependencyInjection() {
        DaggerCategoryListComponent
            .builder()
            .coreComponent(ApplicationClass.coreComponent(requireContext()))
            .categoryListModule(CategoryListModule(this))
            .build()
            .inject(this)
    }

    override fun render(state: CategoryListViewState) {
        when {
            !state.isLoading && (state.error == null) -> presentSuccessState(
                categoryUIModelMapper.mapToUIList(
                    state.data
                )
            )
            state.error != null -> presentErrorState(
                state.error
            )
            state.isLoading -> presentLoadingState()
        }
    }

    private fun presentSuccessState(data: List<CategoryUIModel>) {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.listLoading.hide()

        if (data.isNotEmpty()) {
            binding.emptyView.hide()
            binding.errorView.hide()
            binding.categories.show()
        } else {
            binding.emptyView.show()
            binding.errorView.hide()
            binding.categories.hide()
        }

        categoryListAdapter.submitList(data)
    }

    private fun presentErrorState(error: Throwable) {
        binding.emptyView.hide()
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.hide()
        binding.categories.hide()
        binding.errorView.show()
        error.message?.let {
            binding.errorView.setErrorViewText(it)
        }
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    private fun presentLoadingState() {
        binding.shimmerViewContainer.show()
        binding.shimmerViewContainer.startShimmerAnimation()
        binding.listLoading.show()
        binding.categories.hide()
        binding.emptyView.hide()
        binding.errorView.hide()
    }

    override fun intents(): Flow<CategoryListIntent> {
        return loadInitialIntent
    }

}