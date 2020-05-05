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
package com.aliumujib.artic.data

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.model.AuthorEntity
import com.aliumujib.artic.data.model.CategoryEntity
import com.aliumujib.artic.data.model.CommentEntity
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.models.Author
import com.aliumujib.artic.domain.models.Comment
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

    fun makeRandomArticle(): Article {
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
}
