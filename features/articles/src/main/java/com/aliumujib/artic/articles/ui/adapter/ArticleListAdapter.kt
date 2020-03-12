package com.aliumujib.artic.articles.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import coil.transform.RoundedCornersTransformation
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.databinding.LoadingItemBinding
import com.aliumujib.artic.articles.models.ArticleUIModel
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.show
import com.aliumujib.artic.views.iconandtitle.IconAndTitleView
import timber.log.Timber


class ArticleListAdapter : ListAdapter<ArticleUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private var listState: ListState? = null
    private var viewType: LAYOUT = LAYOUT.GRID

    sealed class ListState(val error: Throwable?) {
        object Loading : ListState(null)
        object Idle : ListState(null)
        data class Error (val throwable: Throwable) : ListState(throwable)
    }

    enum class LAYOUT(val value: Int) {
        GRID(1),
        LIST(2),
        LOADING(3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LAYOUT.GRID.value -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_item_grid, parent, false)
                ArticleViewHolder(view)
            }
            LAYOUT.LIST.value -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.article_item_list, parent, false)
                ArticleViewHolder(view)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LoadingItemBinding.inflate(inflater, parent, false)
                LoadingViewHolder(
                    binding
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Timber.d("Position: ${position} itemType: ${holder.itemViewType}")
        when (holder.itemViewType) {
            LAYOUT.GRID.value -> {
                (holder as ArticleViewHolder).bind(getItem(position))
            }
            LAYOUT.LIST.value -> {
                (holder as ArticleViewHolder).bind(getItem(position))
            }
            LAYOUT.LOADING.value -> {
                (holder as LoadingViewHolder).bind(listState)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            (isLoadingNextPage() && position == itemCount - 1) -> {
                LAYOUT.LOADING.value
            }
            viewType == LAYOUT.LIST -> {
                LAYOUT.LIST.value
            }
            viewType == LAYOUT.GRID -> {
                LAYOUT.GRID.value
            }
            else -> {
                LAYOUT.LIST.value
            }
        }
    }


     fun isLoadingNextPage() = listState != null && listState != ListState.Idle

    fun setListState(newListState: ListState?) {
        val previousState = this.listState
        val hadExtraRow = isLoadingNextPage()
        this.listState = newListState
        val hasExtraRow = isLoadingNextPage()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newListState) {
            notifyItemChanged(itemCount - 1)
        }
    }



    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoadingNextPage()) 1 else 0
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val articleImage = itemView.findViewById<ImageView>(R.id.article_image)
        private val articleCategory = itemView.findViewById<TextView>(R.id.article_category)
        private val articleTitle = itemView.findViewById<TextView>(R.id.article_title)
        private val articleDateTimePublish =
            itemView.findViewById<TextView>(R.id.article_date_time_publish)
        private val commentsButton = itemView.findViewById<IconAndTitleView>(R.id.comments_button)


        fun bind(model: ArticleUIModel) {
            this.articleCategory.text = model.categories.firstOrNull()?.title
            this.articleTitle.text = model.title_plain
            this.articleDateTimePublish.text = model.date.toString()
            this.articleImage.load(model.fullImageURL) {
                transformations(RoundedCornersTransformation(6.0f, 6.0f, 0.0f, 0.0f))
                //error(errorPlaceHolder)
                crossfade(true)
                size(ViewSizeResolver.invoke(articleImage, false))
                scale(Scale.FIT)
            }
        }
    }


    class LoadingViewHolder(
        private val binding: LoadingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ListState?) {
            when (model) {
                is ListState.Idle -> {
                    binding.loading.stopShimmerAnimation()
                    binding.loading.hide()
                    binding.retryLayout.hide()
                }
                is ListState.Loading -> {
                    binding.loading.startShimmerAnimation()
                    binding.loading.show()
                    binding.retryLayout.hide()
                }
                is ListState.Error -> {
                    binding.loading.stopShimmerAnimation()
                    binding.loading.hide()
                    binding.retryLayout.show()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArticleUIModel>() {
        override fun areItemsTheSame(oldItem: ArticleUIModel, newItem: ArticleUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleUIModel, newItem: ArticleUIModel): Boolean {
            return oldItem == newItem
        }
    }
}