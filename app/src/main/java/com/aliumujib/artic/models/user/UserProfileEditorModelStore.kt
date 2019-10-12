package com.aliumujib.artic.models.user

import com.aliumujib.artic.models.ModelStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileEditorModelStore @Inject constructor() : ModelStore<UserProfileEditorState>(
    UserProfileEditorState.EDITING(User("Abdul-Mujeeb Aliu", "aliuabdulmujib@gmail.com"))
)

