package com.aliumujib.artic.articles.test

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Author
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.domain.models.Comment
import konveyor.base.randomBuild

object DummyDataFactory {


    fun makeRandomAuthor(): Author {
        return randomBuild()
    }


    fun makeRandomComment(): Comment {
        return randomBuild()
    }


    fun makeRandomCategory(): Category {
        return randomBuild()
    }

    fun makeArticlesList(count: Int): List<Article> {
        val articles = mutableListOf<Article>()
        repeat(count) {
            articles.add(makeRandomArticle())
        }
        return articles
    }

    fun makeRandomArticle(isBookmarked: Boolean = randomBuild()): Article {
        return Article(
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
            isBookmarked
        )
    }

}