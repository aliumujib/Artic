/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
