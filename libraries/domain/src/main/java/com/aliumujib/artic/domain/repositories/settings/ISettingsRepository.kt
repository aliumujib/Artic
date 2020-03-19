package com.aliumujib.artic.domain.repositories.settings



interface ISettingsRepository {

    suspend fun setCurrentViewMode(isGrid:Boolean): Boolean

    suspend fun getCurrentViewMode(): Boolean

}