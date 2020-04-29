package com.aliumujib.artic.cache.models.mappers

import com.aliumujib.artic.cache.models.ArticleCacheModel
import com.aliumujib.artic.data.model.ArticleEntity
import java.util.*
import javax.inject.Inject

class ArticleCacheModelMapper @Inject constructor(
    private val authorCacheModelMapper: AuthorCacheModelMapper,
    private val categoryCacheModelMapper: CategoryCacheModelMapper
) :
    CacheModelMapper<ArticleCacheModel, ArticleEntity> {

    override fun mapToEntity(model: ArticleCacheModel): ArticleEntity {
        return ArticleEntity(
            model.id,
            model.type,
            model.slug,
            model.imageURL,
            model.status,
            model.title,
            model.title_plain,
            model.content,
            model.excerpt,
            Date(model.date),
            model.modified,
            model.thumbnail,
            model.imageURL,
            model.comment_count,
            categoryCacheModelMapper.mapToEntityList(model.categories),
            authorCacheModelMapper.mapToEntity(model.author),
            model.isBookmarked
        )
    }

    override fun mapToModel(entity: ArticleEntity): ArticleCacheModel {
        return ArticleCacheModel(
            entity.id,
            entity.type,
            entity.slug,
            entity.imageURL,
            entity.status,
            entity.title,
            entity.title_plain,
            entity.content,
            entity.excerpt,
            entity.date.time,
            entity.modified,
            entity.thumbnail,
            entity.imageURL,
            entity.comment_count,
            categoryCacheModelMapper.mapToModelList(entity.categories),
            authorCacheModelMapper.mapToModel(entity.author),
            entity.isBookmarked
        )
    }
}
