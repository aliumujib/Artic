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
package com.aliumujib.artic.articles.models

import com.aliumujib.artic.domain.models.Author
import com.aliumujib.artic.views.models.AuthorUIModel
import com.aliumujib.artic.views.mappers.UIModelMapper
import javax.inject.Inject

class AuthorUIModelMapper @Inject constructor() : UIModelMapper<Author, AuthorUIModel>() {

    override fun mapToUI(entity: Author): AuthorUIModel {
        return AuthorUIModel(
            entity.id, entity.slug, entity.name, entity.first_name, entity.last_name,
            entity.nickname, entity.url, entity.description
        )
    }

    override fun mapFromUI(domain: AuthorUIModel): Author {
        return Author(
            domain.id, domain.slug, domain.name, domain.first_name, domain.last_name,
            domain.nickname, domain.url, domain.description
        )
    }

}
