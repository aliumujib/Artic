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
