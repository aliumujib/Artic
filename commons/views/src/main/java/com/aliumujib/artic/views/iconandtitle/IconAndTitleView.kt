package com.aliumujib.artic.views.iconandtitle

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.aliumujib.artic.views.R
import com.aliumujib.artic.views.ext.hide
import com.aliumujib.artic.views.ext.show

/**
 * Created by aliumujib on 09/06/2018.
 */

class IconAndTitleView : FrameLayout {

    private var view: View
    private var icon: ImageView
    private var title: TextView


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.icon_and_title_view, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.IconAndTitleView, 0, 0)

        val text: String? = a.getString(R.styleable.IconAndTitleView_titleText)
        val resID: Int = a.getResourceId(R.styleable.IconAndTitleView_iconRes, -1)
        val hideTitle: Boolean = a.getBoolean(R.styleable.IconAndTitleView_hideTitle, false)

        a.recycle()

        icon = view.findViewById(R.id.icon)
        title = view.findViewById(R.id.title)

        if (hideTitle) {
            title.hide()
        } else {
            title.show()
        }
        setTitleText(text)
        setIconResId(resID)
    }

    fun setTitleText(titleText: String?) {
        title.text = titleText
    }

    fun setIconResId(@DrawableRes drawableResID: Int) {
        if (drawableResID != -1) {
            icon.setImageResource(drawableResID)
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

    }
}