package com.aliumujib.artic.articledetails.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import coil.transform.RoundedCornersTransformation
import com.aliumujib.artic.articledetails.R
import com.aliumujib.artic.articledetails.databinding.DetailsFragmentBinding
import com.aliumujib.artic.articledetails.di.ArticleDetailsModule
import com.aliumujib.artic.articledetails.di.DaggerArticleDetailsComponent
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsIntent
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewModel
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewState
import com.aliumujib.artic.articles.databinding.ArticleListFragmentBinding
import com.aliumujib.artic.articles.di.ArticleListModule
import com.aliumujib.artic.articles.di.DaggerArticleListComponent
import com.aliumujib.artic.articles.models.ArticleUIModelMapper
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.mvi.MVIView
import com.eyowo.android.core.utils.autoDispose
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import javax.inject.Inject

class ArticleDetailsFragment : Fragment(), MVIView<ArticleDetailsIntent, ArticleDetailsViewState> {

    val articleArgs by navArgs<ArticleDetailsFragmentArgs>()

    @Inject
    lateinit var viewModel: ArticleDetailsViewModel

    @Inject
    lateinit var articleUIModelMapper: ArticleUIModelMapper

    private var _binding by autoDispose<DetailsFragmentBinding>()
    private val binding get() = _binding

    private val _loadInitialIntent = BroadcastChannel<ArticleDetailsIntent>(1)
    private val loadInitialIntent = _loadInitialIntent.asFlow().take(1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.processIntent(intents())

        _loadInitialIntent.offer(
            ArticleDetailsIntent.LoadArticleDetailsIntent(
                articleUIModelMapper.mapFromUI(
                    articleArgs.article
                )
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStates()
    }


    private fun observeStates() {
        viewModel.states()
            .onEach {
                render(it)
            }.launchIn(lifecycleScope)
    }

    override fun render(state: ArticleDetailsViewState) {
        when {
            state.isLoading -> {
                binding.shimmerViewContainer.show()
                binding.shimmerViewContainer.startShimmerAnimation()
                binding.articleViewContainer.hide()
            }
            state.error != null -> {

            }
            !state.isLoading && (state.error == null) -> {
                binding.shimmerViewContainer.stopShimmerAnimation()
                binding.shimmerViewContainer.hide()
                binding.articleViewContainer.show()
                state.data?.let {
                    presentSuccessState(articleUIModelMapper.mapToUI(it))
                }
            }
        }
    }

    private fun presentSuccessState(article: ArticleUIModel) {
        binding.articleCategoryNames.text = article.categories.first().title
        binding.articleName.text = article.title
        binding.articleDateTimePublish.text = article.date.toString()
        binding.articleImage.load(article.fullImageURL) {
            transformations(RoundedCornersTransformation(12.0f, 12.0f, 12.0f, 12.0f))
            //error(errorPlaceHolder)
            crossfade(true)
            size(ViewSizeResolver.invoke(binding.articleImage, false))
        }
        binding.htmlText.setHtml(article.content, HtmlHttpImageGetter(binding.htmlText))
    }

    override fun intents(): Flow<ArticleDetailsIntent> {
        return loadInitialIntent
    }

}
