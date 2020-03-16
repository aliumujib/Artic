package com.aliumujib.artic.views.bookmarkbutton


import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.aliumujib.artic.views.R
import com.aliumujib.artic.views.ext.dpToPx

class BookmarkButtonView : AppCompatImageView {

    private var onBookmarkStatusChangeListener: OnBookmarkStatusChangeListener? = null
    private var bookMarkedDrawable: Drawable? = null
    private var unBookMarkedDrawable: Drawable? = null
    private var padding = 10
    @BookmarkState
    private var currentBookmarkStatus = UNBOOKMARKED

    var isBookMarked: Int
        @BookmarkState
        get() = currentBookmarkStatus
        set(@BookmarkState bookmarked) {
            this.currentBookmarkStatus = bookmarked
            setIcons()
        }


    init {
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeView()
    }

    fun setIsBookmarked(isBookmarked: Boolean) {
        if (isBookmarked) {
            this.isBookMarked = BOOKMARKED
        } else {
            this.isBookMarked = UNBOOKMARKED
        }
    }

    private fun initializeView() {
        bookMarkedDrawable = ContextCompat.getDrawable(context, R.drawable.ic_bookmarked)

        unBookMarkedDrawable = ContextCompat.getDrawable(context, R.drawable.ic_bookmark)


        setIcons()
        padding = context.dpToPx(padding)
        setPadding(padding, padding, padding, padding)

        setOnClickListener(BookmarkClickListener())

    }

    private fun setIcons() {
        background = if (currentBookmarkStatus == BOOKMARKED) {
            bookMarkedDrawable
        } else
            unBookMarkedDrawable
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (Build.VERSION.SDK_INT > 10) {
            alpha = if (enabled) {
                1f
            } else {
                0.5f
            }
        }
    }

    fun setOnBookmarkStatusChangeListener(onBookmarkStatusChangeListener: OnBookmarkStatusChangeListener) {
        this.onBookmarkStatusChangeListener = onBookmarkStatusChangeListener
    }

    @IntDef(UNBOOKMARKED, BOOKMARKED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class BookmarkState

    interface OnBookmarkStatusChangeListener {
        fun onStatusChanged(@BookmarkState isBookmarked: Int)
    }

    private inner class BookmarkClickListener : View.OnClickListener {

        override fun onClick(view: View) {
            currentBookmarkStatus = if (currentBookmarkStatus == BOOKMARKED) {
                UNBOOKMARKED
            } else
                BOOKMARKED

            setIcons()

            if (onBookmarkStatusChangeListener != null)
                onBookmarkStatusChangeListener!!.onStatusChanged(currentBookmarkStatus)
        }
    }

    companion object {
        const val UNBOOKMARKED = 0
        const val BOOKMARKED = 1
    }
}
