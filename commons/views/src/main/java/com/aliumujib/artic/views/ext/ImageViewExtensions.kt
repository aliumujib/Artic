package com.aliumujib.artic.views.ext

import androidx.annotation.DimenRes
import com.aliumujib.artic.views.R
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.enableCornerRadii(@DimenRes radius: Int = R.dimen.one_space) {
    shapeAppearanceModel = shapeAppearanceModel.toBuilder()
        .setAllCornerSizes(resources.getDimension(radius))
        .build()
}


fun ShapeableImageView.enableOnlyTopCornerRadii(@DimenRes radius: Int = R.dimen.one_space) {
    shapeAppearanceModel = shapeAppearanceModel.toBuilder()
        .setTopLeftCornerSize(resources.getDimension(radius))
        .setTopRightCornerSize(resources.getDimension(radius))
        .build()
}