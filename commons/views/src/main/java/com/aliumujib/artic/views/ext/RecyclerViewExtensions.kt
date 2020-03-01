package com.aliumujib.artic.views.ext

import android.widget.ListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Get implementation manger that lays out items in a grid.
 *
 * @return Recycle view grid layout manager if configured, otherwise null.
 */
val RecyclerView.gridLayoutManager: GridLayoutManager?
    get() = layoutManager as? GridLayoutManager

/**
 * Get implementation manager which provides similar functionality [ListView].
 *
 * @return Recycle view linear layout manager if configured, otherwise null.
 */
val RecyclerView.linearLayoutManager: LinearLayoutManager?
    get() = layoutManager as? LinearLayoutManager
