package com.aliumujib.artic.categories.ui.adapter

import com.aliumujib.artic.views.models.CategoryUIModel

interface CategoryClickListener {
    fun onCategoryClick(categoryUIModel: CategoryUIModel)
}