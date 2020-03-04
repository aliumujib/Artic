package com.aliumujib.artic.views.emptyerrorstate

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import com.aliumujib.artic.views.R
import com.aliumujib.artic.views.databinding.SimpleEmptyStateViewLayoutBinding


class SimpleEmptyStateView : LinearLayout {

    private var view: View
    private var actionButtonClickListener: ActionButtonClickListener? = null

    private var _binding: SimpleEmptyStateViewLayoutBinding? = null
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
        _binding = SimpleEmptyStateViewLayoutBinding.inflate(inflater, this, true)
        view = binding.root

        val a = context.obtainStyledAttributes(attrs, R.styleable.SimpleEmptyStateView, 0, 0)
        val emptyStateImageSrc: Drawable? =
            a.getDrawable(R.styleable.SimpleEmptyStateView_emptyStateImageSrc)
        val emptyStateCaption: String? =
            a.getString(R.styleable.SimpleEmptyStateView_emptyStateCaptionText)
        val emptyStateButtonText: String? =
            a.getString(R.styleable.SimpleEmptyStateView_emptyStateButtonText)

        val emptyStateButtonVisible: Boolean =
            a.getBoolean(R.styleable.SimpleEmptyStateView_isButtonVisible, false)

        a.recycle()

        if (emptyStateImageSrc != null) {
            binding.image.setImageDrawable(emptyStateImageSrc)
        } else {
            binding.image.visibility = View.INVISIBLE
        }

        if (emptyStateCaption != null) {
            binding.caption.text = emptyStateCaption
        }

        if (emptyStateButtonText != null) {
            binding.solidBackgroundButtonView.text = emptyStateButtonText
        }

        if (emptyStateButtonVisible) {
            binding.solidBackgroundButtonView.visibility = View.VISIBLE

        } else {
            binding.solidBackgroundButtonView.visibility = View.GONE

        }

        binding.solidBackgroundButtonView.setOnClickListener {
            actionButtonClickListener?.invoke()
        }


        val actionBtnBounceAnim =
            ObjectAnimator.ofFloat(binding.image, "translationY", 0f, 25f, 0f)
        actionBtnBounceAnim.interpolator = AccelerateDecelerateInterpolator()
        actionBtnBounceAnim.duration = 3000
        actionBtnBounceAnim.repeatMode = ValueAnimator.RESTART
        actionBtnBounceAnim.repeatCount = ValueAnimator.INFINITE

        actionBtnBounceAnim.start()

    }


    fun setActionBtnClickListener(actionButtonClickListener: ActionButtonClickListener) {
        this.actionButtonClickListener = actionButtonClickListener
    }

    fun setErrorViewText(text: String) {
        binding.caption.text = text
    }

    interface ActionButtonClickListener {
        fun invoke()
    }

}