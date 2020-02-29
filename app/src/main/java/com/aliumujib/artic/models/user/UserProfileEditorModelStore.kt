package com.aliumujib.artic.models.user

import com.aliumujib.artic.models.ModelStore
import com.aliumujib.artic.models.user.UserProfileEditorState.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileEditorModelStore @Inject constructor() : ModelStore<UserProfileEditorState>(
    EDITING(User("abdul", "aliu@gmail.com"))
)

