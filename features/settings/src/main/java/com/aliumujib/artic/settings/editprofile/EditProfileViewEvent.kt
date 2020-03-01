package com.aliumujib.artic.settings.editprofile

sealed class EditProfileViewEvent {
    data class FullNameChange(val fullname: String) : EditProfileViewEvent()
    data class EmailChange(val email: String) : EditProfileViewEvent()
    object SaveProfileClick : EditProfileViewEvent()
}