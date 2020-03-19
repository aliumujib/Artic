package com.aliumujib.artic.views.loadingview

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.aliumujib.artic.views.R
import com.aliumujib.artic.views.databinding.LoadingViewLayoutBinding


class LoadingView : LinearLayout {

    private var view: View

    private var _binding: LoadingViewLayoutBinding? = null
    private val binding get() = _binding!!

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = LoadingViewLayoutBinding.inflate(inflater, this, true)
        view = binding.root

        val a = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0)

        val caption: String? =
            a.getString(R.styleable.LoadingView_loadingCaptionText)

        a.recycle()

        if (caption != null) {
            binding.caption.text = caption
        }

    }


    fun setLoadingViewText(text: String) {
        binding.caption.text = text
    }


}