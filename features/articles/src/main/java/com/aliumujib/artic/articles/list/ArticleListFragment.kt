package com.aliumujib.artic.articles.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.mobile_ui.ApplicationClass.Companion.coreComponent
import javax.inject.Inject


class ArticleListFragment : Fragment() {


    @Inject
    lateinit var viewModel: ArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDependencyInjection()

    }

    /**
     * Initialize dagger injection dependency graph.
     */
    private fun initDependencyInjection() {
        DaggerArticleListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .charactersListModule(CharactersListModule(this))
            .build()
            .inject(this)
    }


}
