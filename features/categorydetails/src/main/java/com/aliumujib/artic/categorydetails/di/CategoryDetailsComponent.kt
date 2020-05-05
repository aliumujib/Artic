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
package com.aliumujib.artic.categorydetails.di

import com.aliumujib.artic.categorydetails.ui.CategoryDetailsFragment
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CategoryDetailsModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CategoryDetailsModule::class],
    dependencies = [CoreComponent::class])
interface CategoryDetailsComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Article list fragment.
     */
    fun inject(listFragment: CategoryDetailsFragment)
}
