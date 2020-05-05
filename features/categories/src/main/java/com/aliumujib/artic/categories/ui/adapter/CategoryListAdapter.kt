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
package com.aliumujib.artic.categories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.artic.views.models.CategoryUIModel
import com.aliumujib.artic.categories.databinding.CategoryListItemBinding
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject


class CategoryListAdapter @Inject constructor(private val categoryClickListener: CategoryClickListener) :
    ListAdapter<CategoryUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, categoryClickListener)
    }

    fun isEmpty() = super.getItemCount()  == 0


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(getItem(position))
    }


    class CategoryViewHolder(private val binding: CategoryListItemBinding, private val categoryClickListener: CategoryClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CategoryUIModel) {
            itemView.setOnClickListener {
                categoryClickListener.onCategoryClick(model)
            }
            binding.categoryName.text = model.title
            binding.categoryCount.text = model.postCount.toString()
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<CategoryUIModel>() {
        override fun areItemsTheSame(oldItem: CategoryUIModel, newItem: CategoryUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryUIModel,
            newItem: CategoryUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
