package com.eyowo.android.core.domain.usecases

/***
 * Dear Great and Tobenna,
 * I know using this class is tempting, you could wrap this in a coroutine and call it a day.
 * I know you want to do it Tobenna... DON'T!!!
 *
 *
 * If you do, I will bounce your PR.
 *
 * (Or atleast tell me what you need it for and it must be for non blocking stuff like shared prefs)
 *
 * Cheers,
 * Mujeeb.
 *
 * ****/
abstract class NonRxUseCase<in Params, T> {

    abstract fun execute(params: Params? = null): T

    fun dispose() {

    }
}