package com.aliumujib.artic.ui.articles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliumujib.artic.R
import com.aliumujib.artic.presentation.ArticleListViewModel


class ArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
    }

}
