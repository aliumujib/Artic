package com.aliumujib.artic.views.ext


fun <T> List<T>.replaceItemInList(comparator: (item: T) -> Boolean, item: T): List<T> {
    val list = this.toMutableList()
    list.forEachIndexed { index, each ->
        each.takeIf { comparator(each) }?.let {
            list[index] = item
        }
    }
    return list
}