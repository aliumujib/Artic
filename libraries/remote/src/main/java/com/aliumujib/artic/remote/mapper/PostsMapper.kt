package com.aliumujib.artic.remote.mapper

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.remote.models.Post
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PostsMapper @Inject constructor(var categoryMapper: CategoryMapper,
                                      var authorMapper: AuthorMapper
) : RemoteModelMapper<Post, ArticleEntity> {

    override fun mapFromModel(model: Post): ArticleEntity {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: Date = safeParse(format, model.date)

        return ArticleEntity(
            model.id,
            model.type,
            model.slug,
            model.url,
            model.comment_status,
            model.title,
            model.title_plain,
            safeString(model.content),
            model.excerpt,
            date,
            model.modified,
            model.thumbnail,
            safeString(model.thumbnail_images.full?.url),
            model.comment_count,
            categoryMapper.mapModelList(model.categories),
            authorMapper.mapFromModel(model.author),
            false
        )

    }

}