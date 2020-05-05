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
