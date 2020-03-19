package com.aliumujib.artic.cache.impl

import android.content.Context
import com.aliumujib.artic.cache.utils.CoreSharedPrefManager
import com.aliumujib.artic.data.repositories.settings.cache.ISettingsCache
import javax.inject.Inject

class SettingsCacheImpl @Inject constructor(var context: Context) :ISettingsCache, CoreSharedPrefManager(context) {

    override suspend fun setArticleListViewMode(isGridMode: Boolean): Boolean {
        savePref(LAST_CACHE_TIME, isGridMode)
        return isGridMode
    }

    override suspend fun getArticleListViewMode(): Boolean {
        return getPref(LAST_CACHE_TIME, true)!!
    }

    companion object {
        private const val LAST_CACHE_TIME = "LIST_VIEW_MODE"
    }
}