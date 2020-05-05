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
package com.aliumujib.artic.di.modules

import com.aliumujib.artic.BuildConfig
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.api.WordPressServiceFactory
import com.aliumujib.artic.remote.impl.ArticlesRemote
import com.aliumujib.artic.remote.impl.CategoriesRemote
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): WordPressAPI {
            return WordPressServiceFactory.makeWordPressService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsArticlesRemote(remote: ArticlesRemote): IArticlesRemote

    @Binds
    abstract fun bindsCategoriesRemote(remote: CategoriesRemote): ICategoriesRemote

}
