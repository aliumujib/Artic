package com.aliumujib.artic.bookmarks.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.bookmarks.di.BookmarkListModule
import com.aliumujib.artic.bookmarks.di.DaggerBookmarkListComponent
import com.aliumujib.artic.bookmarks.presentation.BookmarkListIntent
import com.aliumujib.artic.bookmarks.presentation.BookmarkListIntent.*
import com.aliumujib.artic.bookmarks.presentation.BookmarkListViewModel
import com.aliumujib.artic.bookmarks.presentation.BookmarkListViewState
import com.aliumujib.artic.categorydetails.ui.CategoryDetailsFragmentDirections
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.basearticlelist.BaseArticleListFragment
import com.aliumujib.artic.views.ext.nonNullObserve
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.mvi.MVIView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.take
import javax.inject.Inject


@ExperimentalCoroutinesApi
class BookmarksFragment : BaseArticleListFragment(), MVIView<BookmarkListIntent, BookmarkListViewState> {


    @Inject
    lateinit var viewModel: BookmarkListViewModel

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private val _loadInitialIntent = BroadcastChannel<BookmarkListIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    private val _listActionIntents = BroadcastChannel<BookmarkListIntent>(1)
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
        _loadInitialIntent.offer(StreamBookmarksIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStates()
    }

    private fun observeStates() {
        nonNullObserve(viewModel.states(), ::render)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    private fun initDependencyInjection() {
        DaggerBookmarkListComponent
            .builder()
            .coreComponent(ApplicationClass.coreComponent(requireContext()))
            .bookmarkListModule(BookmarkListModule(this))
            .build()
            .inject(this)
    }

    override fun intents(): Flow<BookmarkListIntent> {
        return merge(loadInitialIntent, listActionIntents)
    }

    override fun onBookmarkBtnClicked(articleUIModel: ArticleUIModel, isBookmarked: Boolean) {
        _listActionIntents.offer(RemoveBookmarkIntent(articleUIModelMapper.mapFromUI(articleUIModel)))
    }

    override fun onCommentBtnClicked(articleUIModel: ArticleUIModel) {

    }

    override fun onArticleClicked(articleUIModel: ArticleUIModel) {
        findNavController().navigate(BookmarksFragmentDirections.actionBookmarksListFragmentToNavDetails(articleUIModel))
    }

    override fun render(state: BookmarkListViewState) {
        when {
            !state.isLoading && (state.error == null) -> presentSuccessState(articleUIModelMapper.mapToUIList(state.data), state.isGrid)
            state.error != null -> presentErrorState(state.error, false, state.data.isEmpty())
            state.isLoading -> presentLoadingState(false)
        }
    }

}