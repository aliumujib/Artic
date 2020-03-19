package com.aliumujib.artic.domain.usecases.settings


import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.User
import com.aliumujib.artic.domain.repositories.auth.IAuthService
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.repositories.settings.ISettingsRepository
import com.aliumujib.artic.domain.usecases.base.SuspendUseCase
import javax.inject.Inject

class UpdateViewModeSettings @Inject constructor(
    private val settingsRepository: ISettingsRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<UpdateViewModeSettings.Params, Boolean>(postExecutionThread) {

    override suspend fun execute(params: Params?): Boolean {
        if (params == null) throw IllegalStateException("Your params can't be null for this use case")
        return settingsRepository.setCurrentViewMode(params.isGrid)
    }

    data class Params constructor(val isGrid: Boolean) {
        companion object {
            fun make(isGrid: Boolean): Params {
                return Params(isGrid)
            }
        }
    }

}