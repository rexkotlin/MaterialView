package com.ex.materialview.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.ex.materialview.R
import kotlinx.android.synthetic.main.testview_layout.view.*

class TextView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.testview_layout, this)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun setImageView(drawable: Drawable) {
        image.background = drawable
    }

    fun setImageView(color: Int) {
        image.setBackgroundColor(color)
    }

    fun setTextView(test: String) {
        iv.text = test
    }

}