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
package com.aliumujib.artic.bookmarks.di

import com.aliumujib.artic.bookmarks.ui.BookmarksFragment
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [BookmarkListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [BookmarkListModule::class],
    dependencies = [CoreComponent::class])
interface BookmarkListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Bookmarks list fragment.
     */
    fun inject(listFragment: BookmarksFragment)

}
