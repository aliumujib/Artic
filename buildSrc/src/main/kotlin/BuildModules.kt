/**
 * Configuration of build modules
 */
object BuildModules {
    const val MOBILE_UI = ":mobile-ui"
    const val CORE = ":core"

    object Features {
        const val ARTICLES = ":features:articles"
        const val ARTICLE_DETAILS = ":features:articledetails"
        const val ABOUT = ":features:about"
        const val BOOKMARKS = ":features:bookmarks"
        const val CATEGORIES = ":features:categories"
        const val CATEGORY_DETAILS = ":features:categorydetails"
        const val SEARCH = ":features:search"
        const val SETTINGS = ":features:settings"
        const val AUTHORIZATION = ":features:authorization"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }

    object Libraries {
        const val DATA = ":libraries:data"
        const val DOMAIN = ":libraries:domain"
        const val CACHE = ":libraries:cache"
        const val REMOTE = ":libraries:remote"

        const val TEST_UTILS = ":libraries:testutilities"
    }
}
