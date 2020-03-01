package com.aliumujib.artic.domain.repositories.auth

import com.aliumujib.artic.domain.models.User


interface IAuthService {

    suspend fun signInUserViaGoogle(): User?

}