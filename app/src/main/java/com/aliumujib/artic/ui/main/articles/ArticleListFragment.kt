package com.aliumujib.artic.ui.main.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aliumujib.artic.databinding.ArticleListFragmentBinding
import com.aliumujib.artic.presentation.ArticleListViewModel


class ArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel

    private val binding: ArticleListFragmentBinding by lazy {
        ArticleListFragmentBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)

    }

}
