package com.aliumujib.artic.cache

import com.aliumujib.artic.cache.models.ArticleCacheModel
import com.aliumujib.artic.cache.models.AuthorCacheModel
import com.aliumujib.artic.cache.models.CommentCacheModel
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.model.CommentEntity
import konveyor.base.randomBuild
import kotlin.random.Random

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

    fun makeRandomAuthor(): AuthorCacheModel {
        return randomBuild()
    }


    fun makeRandomComment(): CommentCacheModel {
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
        articles.forEach {
            it.isBookmarked = Random.nextBoolean()
        }
        return articles
    }

    fun makeRandomArticle(): ArticleCacheModel {
        return ArticleCacheModel(
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

    fun makeRandomCategoryEntityList(count: Int): List<CategoryEntity> {
        val mutableList = mutableListOf<CategoryEntity>()
        repeat(count) {
            mutableList.add(makeRandomCategoryEntity())
        }
        return mutableList
    }

    fun makeRandomArticleCacheModelList(count: Int): List<ArticleCacheModel> {
        val mutableList = mutableListOf<ArticleCacheModel>()
        repeat(count) {
            mutableList.add(makeRandomArticle())
        }
        return mutableList
    }
}