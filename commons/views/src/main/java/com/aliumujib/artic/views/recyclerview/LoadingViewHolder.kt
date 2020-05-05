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
package com.aliumujib.artic.views.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.artic.views.databinding.LoadingItemBinding
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.show

class LoadingViewHolder(
    private val binding: LoadingItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ListState?) {
        when (model) {
            is ListState.Idle -> {
                binding.loading.hide()
                binding.retryLayout.hide()
            }
            is ListState.Loading -> {
                binding.loading.show()
                binding.retryLayout.hide()
            }
            is ListState.Error -> {
                binding.loading.hide()
                binding.retryLayout.show()
            }
        }
    }
}
