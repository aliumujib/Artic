

package com.aliumujib.artic.categories.di

import com.aliumujib.artic.categories.ui.CategoryListFragment
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CategoryListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CategoryListModule::class],
    dependencies = [CoreComponent::class])
interface CategoryListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Category list fragment.
     */
    fun inject(listFragment: CategoryListFragment)
}
