package com.aliumujib.artic.categories.presentation

sealed class CategoryListAction {

    data class LoadCategoryListAction(val page: Int) : CategoryListAction()

}