package com.aliumujib.artic.views.ext

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.StringRes
import kotlin.math.roundToInt

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


fun Context.dpToPx(dp: Int): Int {
    var displayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

