package com.aliumujib.artic.views.recyclerview

sealed class ListState(val error: Throwable?) {
    object Loading : ListState(null)
    object Idle : ListState(null)
    data class Error(val throwable: Throwable) : ListState(throwable)
}
