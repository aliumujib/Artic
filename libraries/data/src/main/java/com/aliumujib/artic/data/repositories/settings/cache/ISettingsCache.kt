package com.aliumujib.artic.data.repositories.settings.cache

interface ISettingsCache {

    suspend fun setArticleListViewMode(isGridMode: Boolean): Boolean

    suspend fun getArticleListViewMode(): Boolean

}