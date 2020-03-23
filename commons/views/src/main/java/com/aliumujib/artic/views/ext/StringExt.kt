package com.aliumujib.artic.views.ext

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.text.Html
import android.text.Spanned


@SuppressWarnings("deprecation")
fun fromHtml(source: String?): Spanned {
    return if (SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }
}