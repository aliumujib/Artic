/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.views.ext

import android.widget.ListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

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


fun RecyclerView.removeAllDecorations() {
    while (this.itemDecorationCount > 0) {
        this.removeItemDecoration(this.getItemDecorationAt(0))
    }
}



//return true if it's last item visited
 fun RecyclerView.isLastItemDisplaying(): Boolean {
    if (this.adapter!!.itemCount != 0) {
        val lastVisibleItemPosition:Int = getLastVisibleItemPosition()
        return lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == this.adapter!!.itemCount - 1
    }
    return false
}


//get last item
fun RecyclerView.getFirstVisibleItemPosition(): Int {
    return if(layoutManager is StaggeredGridLayoutManager){
        var maxSize = 0
        val visibleItemPositions = (this.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(null)
        for (i in visibleItemPositions.indices) {
            if (i == 0) {
                maxSize = visibleItemPositions[i]
            } else if (visibleItemPositions[i] > maxSize) {
                maxSize = visibleItemPositions[i]
            }
        }
        maxSize
    }else{
        (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }
}

//get last item position
fun RecyclerView.getLastVisibleItemPosition(): Int {
    return if(layoutManager is StaggeredGridLayoutManager){
        var maxSize = 0
        val lastVisibleItemPositions = (this.layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        maxSize
    }else{
        (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
    }
}
