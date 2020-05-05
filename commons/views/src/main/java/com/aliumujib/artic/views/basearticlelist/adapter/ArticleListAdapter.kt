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
package com.aliumujib.artic.views.basearticlelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.aliumujib.artic.views.R
import com.aliumujib.artic.views.bookmarkbutton.BookmarkButtonView
import com.aliumujib.artic.views.databinding.LoadingItemBinding
import com.aliumujib.artic.views.ext.enableCornerRadii
import com.aliumujib.artic.views.ext.enableOnlyTopCornerRadii
import com.aliumujib.artic.views.iconandtitle.IconAndTitleView
import com.aliumujib.artic.views.models.ArticleUIModel
import com.aliumujib.artic.views.recyclerview.ListState
import com.aliumujib.artic.views.recyclerview.LoadingViewHolder
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.withContext

class ArticleListAdapter(private val articleClicks: ArticleClickListener) :
    ListAdapter<ArticleUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private var listState: ListState? = null
    private var viewType: LAYOUT = LAYOUT.GRID


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
                ArticleViewHolder(view, articleClicks)
            }
            LAYOUT.LIST.value -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.article_item_list, parent, false)
                ArticleViewHolder(view, articleClicks)
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
        when (holder.itemViewType) {
            LAYOUT.GRID.value -> {
                (holder as ArticleViewHolder).bind(getItem(position), true)
            }
            LAYOUT.LIST.value -> {
                (holder as ArticleViewHolder).bind(getItem(position), false)
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

    fun isEmpty() = super.getItemCount() == 0

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

    fun setLayout(layout: LAYOUT) {
        viewType = layout
        this.notifyItemRangeChanged(0, itemCount)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoadingNextPage()) 1 else 0
    }


    class ArticleViewHolder(itemView: View, var articleClicks: ArticleClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val articleImage = itemView.findViewById<ShapeableImageView>(R.id.article_image)
        private val articleCategory = itemView.findViewById<TextView>(R.id.article_category)
        private val articleTitle = itemView.findViewById<TextView>(R.id.article_title)
        private val articleDateTimePublish =
            itemView.findViewById<TextView>(R.id.article_date_time_publish)
        private val commentsButton = itemView.findViewById<IconAndTitleView>(R.id.comments_button)
        private val bookmarkIcon = itemView.findViewById<BookmarkButtonView>(R.id.bookmark_icon)
        private val shareIcon = itemView.findViewById<IconAndTitleView>(R.id.share_icon)

        private fun getTitle(isGrid: Boolean, commentCount: Int): String {
            return if (isGrid) {
                commentCount.toString()
            } else {
                itemView.context.resources.getQuantityString(R.plurals.comments_count, commentCount, commentCount)
            }
        }

        fun bind(model: ArticleUIModel, isGrid: Boolean) {
            this.articleImage.setOnClickListener {
                articleClicks.onArticleClicked(model)
            }
            this.bookmarkIcon.setOnBookmarkStatusChangeListener(object :
                BookmarkButtonView.OnBookmarkStatusChangeListener {
                override fun onStatusChanged(isBookmarked: Int) {
                    articleClicks.onBookmarkBtnClicked(model, model.isBookmarked)
                }
            })
            this.shareIcon.setOnClickListener {
                articleClicks.onShareBtnClicked(model)
            }
            this.commentsButton.setOnClickListener {
                articleClicks.onCommentBtnClicked(model)
            }
            this.commentsButton.setTitleText(getTitle(isGrid, model.comment_count))
            this.bookmarkIcon.setIsBookmarked(model.isBookmarked)
            this.articleCategory.text = model.categories.firstOrNull()?.title
            this.articleTitle.text = model.titleHtml
            this.articleDateTimePublish.text = model.dateString

            when {
                isGrid -> {
                    this.articleImage.enableOnlyTopCornerRadii(R.dimen.half_space)
                }
                else -> {
                    this.articleImage.enableCornerRadii(R.dimen.half_space)
                }
            }

            this.articleImage.load(model.fullImageURL) {
                //error(errorPlaceHolder)
                crossfade(true)
                size(ViewSizeResolver.invoke(articleImage, false))
                scale(Scale.FIT)
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
