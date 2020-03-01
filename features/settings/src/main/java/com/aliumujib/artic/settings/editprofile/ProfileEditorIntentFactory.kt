package com.aliumujib.artic.settings.editprofile

import com.aliumujib.artic.settings.models.Intent
import com.aliumujib.artic.settings.models.intent
import com.aliumujib.artic.settings.models.user.UserProfileEditorModelStore
import com.aliumujib.artic.settings.models.user.UserProfileEditorState
import com.aliumujib.artic.settings.models.user.UserProfileEditorState.*
import com.aliumujib.artic.settings.editprofile.EditProfileViewEvent.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileEditorIntentFactory @Inject constructor(private val userProfileEditorModelStore: UserProfileEditorModelStore) {

    fun process(viewEvent: com.aliumujib.artic.settings.editprofile.EditProfileViewEvent) {
        userProfileEditorModelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: com.aliumujib.artic.settings.editprofile.EditProfileViewEvent): Intent<UserProfileEditorState> {
        return when (viewEvent) {
            is FullNameChange -> buildFullNameChangeIntent(viewEvent)
            is EmailChange -> buildEmailChangeIntent(viewEvent)
            SaveProfileClick -> TODO()
        }
    }

   /* private fun buildSaveIntent(): Intent<UserProfileEditorState> {
        return editorIntent<EDITING> {
            save()
        }
    }
*/

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