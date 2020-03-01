package com.aliumujib.artic.settings.models.user

import com.aliumujib.artic.settings.models.ModelStore
import com.aliumujib.artic.settings.models.user.UserProfileEditorState.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileEditorModelStore @Inject constructor() : ModelStore<UserProfileEditorState>(
    EDITING(User("abdul", "aliu@gmail.com"))
)

