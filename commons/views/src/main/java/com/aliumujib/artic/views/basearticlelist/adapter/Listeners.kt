package com.aliumujib.artic.views.basearticlelist.adapter

import com.aliumujib.artic.views.models.ArticleUIModel


interface ArticleClickListener {
    fun onArticleClicked(articleUIModel: ArticleUIModel)
    fun onBookmarkBtnClicked(articleUIModel: ArticleUIModel, isBookmarked:Boolean)
    fun onShareBtnClicked(articleUIModel: ArticleUIModel)
    fun onCommentBtnClicked(articleUIModel: ArticleUIModel)
}
