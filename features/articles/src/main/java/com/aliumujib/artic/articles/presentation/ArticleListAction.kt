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
package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article

sealed class ArticleListAction {
    data class SetArticleBookmarkStatusAction(val article: Article, val isBookmarked :Boolean) : ArticleListAction()
    data class LoadArticleListAction(val page: Int) : ArticleListAction()
    data class SwitchArticleListViewModeAction(val isGrid:Boolean) : ArticleListAction()
    object RefreshArticleListAction : ArticleListAction()
    data class FetchMoreArticleListAction(val page: Int) : ArticleListAction()
}
