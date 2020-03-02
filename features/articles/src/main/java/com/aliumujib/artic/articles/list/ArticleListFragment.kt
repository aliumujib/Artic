package com.aliumujib.artic.articles.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ArticleListFragment : Fragment() {


    @Inject
    lateinit var viewModel: ArticleListViewModel

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.statesFlow.collect {
                print("SIZE: ${it.data.size}")
            }
        }

        viewModel.processIntent(ArticleListIntent.LoadArticleListIntent(true))
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
