package com.aliumujib.artic.ui.main.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aliumujib.artic.R
import com.aliumujib.artic.presentation.ArticleListViewModel
import kotlinx.android.synthetic.main.article_list_fragment.view.*


class ArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list_fragment, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)

    }

}
