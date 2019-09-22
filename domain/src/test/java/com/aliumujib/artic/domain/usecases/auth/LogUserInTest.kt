package com.aliumujib.artic.domain.usecases.auth

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.auth.IAuthService
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LogUserInTest {

    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    lateinit var authRepository: IAuthService

    lateinit var signUserIn: SignUserIn

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        signUserIn = SignUserIn(authRepository, postExecutionThread)
    }

    @Test
    fun `check that calling signInUserViaGoogle completes`() {
        val completable = Completable.defer {
            Completable.complete()
        }
        stubLoginCompletable(completable)
        val testCompletable = signUserIn.buildUseCaseCompletable().test()
        testCompletable.assertComplete()
    }


    fun stubLoginCompletable(completable: Completable) {
        whenever(authRepository.signInUserViaGoogle()).thenReturn(completable)
    }
}