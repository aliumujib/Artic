package com.aliumujib.artic.articles.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.aliumujib.artic.articles.R
import com.aliumujib.artic.articles.models.ArticleUIModel
import com.aliumujib.artic.views.iconandtitle.IconAndTitleView


class ArticleListAdapter() : ListAdapter<ArticleUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private val listState: ListState? = null
    private val viewType: LAYOUT = LAYOUT.GRID

    sealed class ListState {
        sealed class Loading : ListState()
        sealed class Idle : ListState()
        sealed class Error(val error: Throwable) : ListState()
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
            else -> ArticleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.article_item_list,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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
            viewType == LAYOUT.LIST -> {
                LAYOUT.LIST.value
            }
            viewType == LAYOUT.GRID -> {
                LAYOUT.GRID.value
            }
            isLoadingMore() -> {
                LAYOUT.LOADING.value
            }
            else -> {
                LAYOUT.LIST.value
            }
        }
    }

    private fun isLastPosition(): Boolean {
        return true
    }

    private fun isLoadingMore(): Boolean {
        return true
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


    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(model: ListState?) {

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