package com.aliumujib.artic.domain.testutils

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Author
import com.aliumujib.artic.domain.models.Category
import konveyor.base.randomBuild

object ArticleDataFactory {

    fun makeCategory(): Category {
        return randomBuild()
    }

    fun makeAuthor(): Author {
        return randomBuild()
    }

    fun makeProject(): Article {
        return randomBuild()
    }

    fun makeCategoryList(count: Int): List<Category> {
        val categories = mutableListOf<Category>()
        repeat(count) {
            categories.add(makeCategory())
        }
        return categories
    }

    fun makeArticlesList(count: Int): List<Article> {
        val articles = mutableListOf<Article>()
        repeat(count) {
            articles.add(makeProject())
        }
        return articles
    }

}