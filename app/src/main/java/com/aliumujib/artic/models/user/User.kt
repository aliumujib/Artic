package com.aliumujib.artic.models.user

data class User(val fullname: String, val email: String)

sealed class UserProfileEditorState {

    object IDLE : UserProfileEditorState() {
        fun editProfile(user: User) = EDITING(user)

        override fun toString(): String {
            return "IDLE"
        }
    }

    data class EDITING(val user: User) : UserProfileEditorState() {
        fun edit(block: User.() -> User) = copy(user = user.block())
        fun save() = SAVING(user)
    }


    data class SAVING(val user: User) : UserProfileEditorState() {
        fun saved() = IDLE
    }

    data class ERROR(val throwable: Throwable) : UserProfileEditorState()
}

