package com.aliumujib.artic.domain.usecases.base


abstract class SynchronousUseCase<in Params, T> {

    abstract fun execute(params: Params? = null): T

}