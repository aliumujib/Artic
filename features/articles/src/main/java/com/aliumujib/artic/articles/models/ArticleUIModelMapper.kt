package com.aliumujib.artic.articles.models

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.models.mappers.UIModelMapper
import javax.inject.Inject

class ArticleUIModelMapper @Inject constructor(
    private val authorUIModelMapper: AuthorUIModelMapper,
    private val categoryUIModelMapper: CategoryUIModelMapper
) : UIModelMapper<Article, ArticleUIModel>() {

    override fun mapToUI(entity: Article): ArticleUIModel {
        return ArticleUIModel(
            entity.id,
            entity.type,
            entity.slug,
            entity.url,
            entity.status,
            entity.title,
            entity.title_plain,
            entity.content,
            entity.excerpt,
            entity.date,
            entity.modified,
            entity.thumbnail,
            entity.fullImageURL,
            entity.comment_count,
            categoryUIModelMapper.mapToUIList(entity.categories),
            authorUIModelMapper.mapToUI(entity.author),
            entity.isBookmarked
        )
    }

    override fun mapFromUI(domain: ArticleUIModel): Article {
        return Article(
            domain.id,
            domain.type,
            domain.slug,
            domain.url,
            domain.status,
            domain.title,
            domain.title_plain,
            domain.content,
            domain.excerpt,
            domain.date,
            domain.modified,
            domain.thumbnail,
            domain.fullImageURL,
            domain.comment_count,
            categoryUIModelMapper.mapFromUIList(domain.categories),
            authorUIModelMapper.mapFromUI(domain.author),
            domain.isBookmarked
        )
    }


}