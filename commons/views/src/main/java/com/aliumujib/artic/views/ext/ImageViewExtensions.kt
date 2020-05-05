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
