package com.aliumujib.artic.remote.utils

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.model.CommentEntity
import com.aliumujib.artic.remote.models.Author
import com.aliumujib.artic.remote.models.Comment
import com.aliumujib.artic.remote.models.Post
import konveyor.base.randomBuild

object DummyDataFactory {

    fun makeRandomArticleEntity(): ArticleEntity {
        return ArticleEntity(
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            mutableListOf(),
            randomBuild(),
            randomBuild()
        )
    }

    fun makeRandomCommentEntity(): CommentEntity {
        return randomBuild()
    }

    fun makeRandomAuthorEntity(): AuthorEntity {
        return randomBuild()
    }

    fun makeRandomAuthor(): Author {
        return randomBuild()
    }

    fun makeRandomPost(): Post {
        return randomBuild()
    }

    fun makeRandomComment(): Comment {
        return randomBuild()
    }


    fun makeRandomCategoryEntity(): CategoryEntity {
        return randomBuild()
    }

    fun makeArticlesEntitiesList(count: Int): List<ArticleEntity> {
        val articles = mutableListOf<ArticleEntity>()
        repeat(count) {
            articles.add(makeRandomArticleEntity())
        }
        return articles
    }


    fun makePostList(count: Int): List<Post> {
        val list = mutableListOf<Post>()
        repeat(count) {
            list.add(makeRandomPost())
        }
        return list
    }


}