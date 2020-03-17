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