/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.categories.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliumujib.artic.views.models.CategoryUIModel
import com.aliumujib.artic.articles.models.CategoryUIModelMapper
import com.aliumujib.artic.categories.databinding.FragmentCategoriesBinding
import com.aliumujib.artic.categories.di.CategoryListModule
import com.aliumujib.artic.categories.di.DaggerCategoryListComponent
import com.aliumujib.artic.categories.presentation.CategoryListIntent
import com.aliumujib.artic.categories.presentation.CategoryListViewModel
import com.aliumujib.artic.categories.presentation.CategoryListViewState
import com.aliumujib.artic.categories.ui.adapter.CategoryClickListener
import com.aliumujib.artic.categories.ui.adapter.CategoryListAdapter
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.ext.*
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.recyclerview.ListSpacingItemDecorator
import com.aliumujib.artic.views.cleanup.autoDispose
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CategoryListFragment : Fragment(), MVIView<CategoryListIntent, CategoryListViewState>, CategoryClickListener {

    @Inject
    lateinit var categoryListAdapter: CategoryListAdapter

    @Inject
    lateinit var viewModel: CategoryListViewModel

    @Inject
    lateinit var categoryUIModelMapper: CategoryUIModelMapper

    private var _binding: FragmentCategoriesBinding by autoDispose()
    private val binding get() = _binding

    private val _loadInitialIntent = ConflatedBroadcastChannel<CategoryListIntent>()
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.processIntent(intents())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()

        nonNullObserve(viewModel.states(), ::render)

        _loadInitialIntent.offer(CategoryListIntent.LoadCategoriesListIntent)
    }

    private fun initializeViews() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.categories.apply {
            removeAllDecorations()
            addItemDecoration(
                ListSpacingItemDecorator(
                    context.dpToPx(32),
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
        binding.loading.hide()

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
        binding.loading.hide()
        binding.categories.hide()
        binding.errorView.show()
        error.message?.let {
            binding.errorView.setErrorViewText(it)
        }
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    private fun presentLoadingState() {
        binding.loading.show()
        binding.categories.hide()
        binding.emptyView.hide()
        binding.errorView.hide()
    }

    override fun intents(): Flow<CategoryListIntent> {
        return loadInitialIntent.filter { categoryListAdapter.isEmpty() }
    }

    override fun onCategoryClick(categoryUIModel: CategoryUIModel) {
        findNavController().navigate(CategoryListFragmentDirections.actionCategoryListFragmentToCategoryDetailsFragment(categoryUIModel))
    }

    override fun onDestroy() {
        _binding.categories.adapter = null
        super.onDestroy()
    }
}
