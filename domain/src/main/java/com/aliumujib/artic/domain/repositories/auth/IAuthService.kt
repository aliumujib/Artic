package com.aliumujib.artic.domain.repositories.auth

import io.reactivex.Completable


interface IAuthService {

    fun signInUserViaGoogle(): Completable

}