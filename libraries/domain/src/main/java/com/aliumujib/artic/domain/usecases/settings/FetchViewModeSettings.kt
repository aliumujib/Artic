package com.aliumujib.artic.domain.usecases.settings


import com.aliumujib.artic.domain.repositories.settings.ISettingsRepository
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.base.SuspendUseCase
import javax.inject.Inject

class FetchViewModeSettings @Inject constructor(
    private val settingsRepository: ISettingsRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<Nothing, Boolean>(postExecutionThread) {

    override suspend fun execute(params: Nothing?): Boolean {
        return settingsRepository.getCurrentViewMode()
    }


}