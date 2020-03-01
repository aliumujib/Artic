package com.aliumujib.artic.views.ext

import android.content.Context
import androidx.annotation.StringRes

/**
 * Get resource string from optional id
 *
 * @param resId Resource string identifier.
 * @return The key value if exist, otherwise empty.
 */
fun Context.getString(@StringRes resId: Int?) =
    resId?.let {
        getString(it)
    } ?: run {
        ""
    }
