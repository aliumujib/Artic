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
package com.aliumujib.artic.articledetails.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.size.ViewSizeResolver
import com.aliumujib.artic.views.R
import com.aliumujib.artic.articledetails.databinding.DetailsFragmentBinding
import com.aliumujib.artic.articledetails.di.ArticleDetailsModule
import com.aliumujib.artic.articledetails.di.DaggerArticleDetailsComponent
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsIntent
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsIntent.*
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewModel
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewState
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.bookmarkbutton.BookmarkButtonView
import com.aliumujib.artic.views.ext.enableCornerRadii
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.nonNullObserve
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.mvi.MVIView
import com.aliumujib.artic.views.cleanup.autoDispose
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.take
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import timber.log.Timber
import javax.inject.Inject


class ArticleDetailsFragment : Fragment(), MVIView<ArticleDetailsIntent, ArticleDetailsViewState> {

    private var _bookmarkBtn: BookmarkButtonView? = null
    private val articleArgs by navArgs<ArticleDetailsFragmentArgs>()

    @Inject
    lateinit var viewModel: ArticleDetailsViewModel

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private var _binding by autoDispose<DetailsFragmentBinding>()
    private val binding get() = _binding

    private val _loadInitialIntent = BroadcastChannel<ArticleDetailsIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    private val _actionMenuIntents = BroadcastChannel<ArticleDetailsIntent>(1)
    private val actionMenuIntents = _actionMenuIntents.asFlow()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        DaggerArticleDetailsComponent
            .builder()
            .coreComponent(ApplicationClass.coreComponent(requireContext()))
            .articleDetailsModule(ArticleDetailsModule(this))
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.processActions()
        setHasOptionsMenu(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.processIntent(intents())

        _loadInitialIntent.offer(
            LoadArticleDetailsIntent(articleUIModelMapper.mapFromUI(articleArgs.article))
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStates()
    }

    private fun observeStates() {
        nonNullObserve(viewModel.states(), ::render)
    }

    override fun render(state: ArticleDetailsViewState) {
        Timber.d("${state::class.java.canonicalName}")
        when {
            state.isLoadingArticleData -> {
                binding.shimmerViewContainer.show()
                binding.shimmerViewContainer.startShimmerAnimation()
                binding.articleViewContainer.hide()
            }
            state.error != null -> {

            }
            !state.isLoadingArticleData -> {
                state.data?.let {
                    presentSuccessState(articleUIModelMapper.mapToUI(it))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.aliumujib.artic.articledetails.R.menu.main_article_details, menu)
        _bookmarkBtn = menu.findItem(com.aliumujib.artic.articledetails.R.id.action_bookmark_article).actionView as BookmarkButtonView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.aliumujib.artic.articledetails.R.id.action_bookmark_article -> {
                _actionMenuIntents.offer(SetArticleBookmarkStatusIntent(articleUIModelMapper.mapFromUI(articleArgs.article), articleArgs.article.isBookmarked))
                return true
            }
            com.aliumujib.artic.articledetails.R.id.action_share_article -> {
                share(articleArgs.article)
                return true
            }
        }
        return false
    }

    private fun share(articleUIModel: ArticleUIModel) {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setText(articleUIModel.url)
            .intent
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun presentSuccessState(article: ArticleUIModel) {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.hide()
        binding.articleViewContainer.show()
        binding.articleCategoryNames.text = article.categories.first().title
        binding.articleName.text = article.titleHtml
        binding.articleDateTimePublish.text = article.dateString
        binding.articleImage.enableCornerRadii(R.dimen.half_space)
        binding.articleImage.load(article.fullImageURL) {
            //error(errorPlaceHolder)
            crossfade(true)
            size(ViewSizeResolver.invoke(binding.articleImage, false))
        }
        _bookmarkBtn?.setIsBookmarked(article.isBookmarked)
        binding.htmlText.setHtml(article.content, HtmlHttpImageGetter(binding.htmlText))
    }

    override fun intents(): Flow<ArticleDetailsIntent> {
        return merge(actionMenuIntents, loadInitialIntent)
    }

}
