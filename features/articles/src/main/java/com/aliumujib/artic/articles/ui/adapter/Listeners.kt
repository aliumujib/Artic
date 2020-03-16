package com.aliumujib.artic.articles.ui.adapter

import com.aliumujib.artic.views.models.ArticleUIModel


interface ArticleClickListener {
    fun onArticleClicked(articleUIModel: ArticleUIModel)
    fun onBookmarkBtnClicked(articleUIModel: ArticleUIModel, isBookmarked:Boolean)
    fun onShareBtnClicked(articleUIModel: ArticleUIModel)
}
