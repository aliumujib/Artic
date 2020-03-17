package com.aliumujib.artic.views.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

internal val View.inflater: LayoutInflater get() = LayoutInflater.from(context)


fun View.hide() {
    this.animate().translationY(-this.bottom.toFloat())
        .setInterpolator(AccelerateInterpolator())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })
        .start()
}

fun View.show() {
    this.animate().translationY(0F)
        .setInterpolator(DecelerateInterpolator())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.VISIBLE
            }
        })
        .start()
}



// slide the view from below itself to the current position
fun View.slideUp() {
    this.visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        this.height.toFloat(), // fromYDelta
        0f
    )                // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
}

// slide the view from its current position to below itself
fun View.slideDown() {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        0f, // fromYDelta
        this.height.toFloat()
    ) // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
    this.visibility = View.GONE
}