

package com.aliumujib.artic.articles.di

import com.aliumujib.artic.articles.ui.ArticleListFragment
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [ArticleListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [ArticleListModule::class],
    dependencies = [CoreComponent::class])
interface ArticleListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Article list fragment.
     */
    fun inject(listFragment: ArticleListFragment)
}
