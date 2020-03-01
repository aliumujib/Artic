package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.domain.models.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor(
    var authorEntityMapper: AuthorEntityMapper,
    var categoryEntityMapper: CategoryEntityMapper
) : EntityMapper<ArticleEntity, Article>() {

    override fun mapFromEntity(entity: ArticleEntity): Article {
        return Article(
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
            entity.imageURL,
            entity.comment_count,
            categoryEntityMapper.mapFromEntityList(entity.categories),
            authorEntityMapper.mapFromEntity(entity.author),
            entity.isBookmarked
        )
    }

    override fun mapToEntity(domain: Article): ArticleEntity {
        return ArticleEntity(
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
            categoryEntityMapper.mapFromDomainList(domain.categories),
            authorEntityMapper.mapToEntity(domain.author),
            domain.isBookmarked
        )
    }

}