package com.aliumujib.artic.ui.main.settings.editprofile

import com.aliumujib.artic.models.Intent
import com.aliumujib.artic.models.intent
import com.aliumujib.artic.models.user.UserProfileEditorModelStore
import com.aliumujib.artic.models.user.UserProfileEditorState
import com.aliumujib.artic.models.user.UserProfileEditorState.EDITING
import com.aliumujib.artic.ui.main.settings.editprofile.EditProfileViewEvent.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileEditorIntentFactory @Inject constructor(private val userProfileEditorModelStore: UserProfileEditorModelStore) {

    fun process(viewEvent: EditProfileViewEvent) {
        userProfileEditorModelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: EditProfileViewEvent): Intent<UserProfileEditorState> {
        return when (viewEvent) {
            is FullNameChange -> buildFullNameChangeIntent(viewEvent)
            is EmailChange -> buildEmailChangeIntent(viewEvent)
            SaveProfileClick -> TODO()
        }
    }


    private fun buildEmailChangeIntent(viewEvent: EmailChange): Intent<UserProfileEditorState> {
        return editorIntent<EDITING> {
            edit {
                copy(email = viewEvent.email)
            }
        }
    }

    private fun buildFullNameChangeIntent(viewEvent: FullNameChange): Intent<UserProfileEditorState> {
        return editorIntent<EDITING> {
            edit {
                copy(fullname = viewEvent.fullname)
            }
        }
    }

    companion object {

        inline fun <reified S : UserProfileEditorState> editorIntent(
            crossinline block: S.() -> UserProfileEditorState
        ): Intent<UserProfileEditorState> {
            return intent {
                (this as? S)?.block()
                    ?: throw IllegalStateException("editorIntent encountered an inconsistent State. [Looking for ${S::class.java} but was ${this.javaClass}]")
            }
        }
    }
}