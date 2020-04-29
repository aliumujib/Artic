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
