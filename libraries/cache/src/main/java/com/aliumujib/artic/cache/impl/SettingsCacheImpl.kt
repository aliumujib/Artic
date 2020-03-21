package com.aliumujib.artic.cache.impl

import android.content.Context
import com.aliumujib.artic.cache.utils.CoreSharedPrefManager
import com.aliumujib.artic.data.repositories.settings.cache.ISettingsCache
import javax.inject.Inject

class SettingsCacheImpl @Inject constructor(var context: Context) :ISettingsCache, CoreSharedPrefManager(context) {

    override suspend fun setArticleListViewMode(isGridMode: Boolean): Boolean {
        savePref(LIST_VIEW_MODE, isGridMode)
        return isGridMode
    }

    override suspend fun getArticleListViewMode(): Boolean {
        return getPref(LIST_VIEW_MODE, true)!!
    }

    companion object {
        private const val LIST_VIEW_MODE = "LIST_VIEW_MODE"
    }
}