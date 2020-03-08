package com.aliumujib.artic.categories.presentation

import com.aliumujib.artic.views.mvi.MVIIntent


sealed class CategoryListIntent : MVIIntent {

    object LoadCategoriesListIntent : CategoryListIntent()

}