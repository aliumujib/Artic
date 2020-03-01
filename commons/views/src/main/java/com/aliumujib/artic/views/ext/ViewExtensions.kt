package com.aliumujib.artic.views.ext

import android.view.LayoutInflater
import android.view.View

internal val View.inflater: LayoutInflater get() = LayoutInflater.from(context)
