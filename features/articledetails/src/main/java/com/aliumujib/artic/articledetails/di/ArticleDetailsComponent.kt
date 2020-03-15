

package com.aliumujib.artic.articledetails.di

import com.aliumujib.artic.articledetails.details.ArticleDetailsFragment
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [ArticleDetailsModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [ArticleDetailsModule::class],
    dependencies = [CoreComponent::class])
interface ArticleDetailsComponent {

    /**
     * Inject dependencies on component.
     *
     * @param fragment Article Details fragment.
     */
    fun inject(fragment: ArticleDetailsFragment)

}
