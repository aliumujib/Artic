package com.aliumujib.artic.categories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.artic.articles.models.CategoryUIModel
import com.aliumujib.artic.categories.databinding.CategoryListItemBinding
import javax.inject.Inject


class CategoryListAdapter @Inject constructor() :
    ListAdapter<CategoryUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(getItem(position))
    }


    class CategoryViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CategoryUIModel) {
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