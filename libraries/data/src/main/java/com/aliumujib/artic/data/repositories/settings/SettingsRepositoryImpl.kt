package com.aliumujib.artic.data.repositories.settings

import com.aliumujib.artic.data.repositories.settings.cache.ISettingsCache
import com.aliumujib.artic.domain.repositories.settings.ISettingsRepository
import javax.inject.Inject


class SettingsRepositoryImpl @Inject constructor(var settingsCache: ISettingsCache) :
    ISettingsRepository {

    override suspend fun setCurrentViewMode(isGrid: Boolean): Boolean {
        return settingsCache.setArticleListViewMode(isGrid)
    }

    override suspend fun getCurrentViewMode(): Boolean {
        return settingsCache.getArticleListViewMode()
    }

}