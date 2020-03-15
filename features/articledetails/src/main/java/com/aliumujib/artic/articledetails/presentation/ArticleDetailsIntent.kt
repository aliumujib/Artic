package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIIntent

sealed class ArticleDetailsIntent : MVIIntent {

    data class LoadArticleDetailsIntent(val article: Article) : ArticleDetailsIntent()
    data class RefreshArticleDetailsIntent(val article: Article) : ArticleDetailsIntent()
    data class BookmarkArticleIntent(val article: Article) : ArticleDetailsIntent()
    data class UnBookmarkArticleIntent(val article: Article) : ArticleDetailsIntent()

}