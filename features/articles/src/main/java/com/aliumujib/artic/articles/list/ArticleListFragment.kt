package com.aliumujib.artic.articles.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.list.adapter.ArticleListAdapter
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import com.aliumujib.artic.views.ext.dpToPx
import com.aliumujib.artic.views.ext.removeAllDecorations
import com.aliumujib.artic.views.recyclerview.SpacingItemDecoration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ArticleListFragment : Fragment() {


    @Inject
    lateinit var viewModel: ArticleListViewModel

    @Inject
    lateinit var articlesAdapter: ArticleListAdapter

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_list_fragment, container, false)
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

        val rv = view.findViewById<RecyclerView>(R.id.articles)
        rv.apply {
            removeAllDecorations()
            addItemDecoration(SpacingItemDecoration(context.dpToPx(16),
                context.dpToPx(16), doubleFirstItemLeftMargin = false, isVertical = true
            ))
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = articlesAdapter
        }

        lifecycleScope.launchWhenResumed {
            viewModel.statesFlow.collect {
                articlesAdapter.submitList(articleUIModelMapper.mapToUIList(it.data))
            }
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


}
