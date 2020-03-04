package com.aliumujib.artic.views.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator

internal val View.inflater: LayoutInflater get() = LayoutInflater.from(context)


fun View.hide(setVisibility: Boolean = false) {
    this.animate().translationY(-this.bottom.toFloat())
        .setInterpolator(AccelerateInterpolator())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (setVisibility) {
                    visibility = View.GONE
                }
            }
        })
        .start()
}

fun View.show(setVisibility: Boolean = false) {
    this.animate().translationY(0F)
        .setInterpolator(DecelerateInterpolator())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (setVisibility) {
                    visibility = View.VISIBLE
                }
            }
        })
        .start()
}
