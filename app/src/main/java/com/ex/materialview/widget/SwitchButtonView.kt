package com.ex.materialview.widget

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import com.ex.materialview.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
class SwitchButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyleAttr
) {

    init {
        background = resources.getDrawable(R.drawable.shape_switch_btn_bg)
    }

    private var btns : MutableList<Button> = mutableListOf()
    private var selectBtn: Button? = null
    private val onItemSelectCallBack: PublishSubject<Int> = PublishSubject.create<Int>()

    fun getSelectCallBack(): Observable<Int> {
        return onItemSelectCallBack
    }
    fun setItem(data: List<String>) {
        this.btns.clear()

        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)

        data.forEachIndexed { index, s ->
            val button = Button(context)
            button.text = s
            button.background = resources.getDrawable(R.mipmap.download)
            setBtnTextColor(button, false)
            button.setOnClickListener {
                selectBtn = it as Button
                onItemSelectCallBack.onNext(index)
                updateItemStatus()
            }
            if (index == 0) {
                selectBtn = button
            }
            addView(button, params)
            this.btns.add(button)
        }

        updateItemStatus()
    }

    fun setSelectItem(position: Int) {
//        if (btns.isNotOutOfBound(position)) {
            selectBtn = btns[position]
            updateItemStatus()
//        }
    }

    private fun updateItemStatus() {
        btns.forEach {
            if (selectBtn == it) {
                setBtnTextColor(it, true)
                it.isEnabled = false
            } else {
                setBtnTextColor(it, false)
                it.isEnabled = true
            }
        }
    }

    private fun setBtnTextColor(btn: Button, isSelect: Boolean) {
        resources.getColor(R.color.switch_thumb_normal_material_dark)
        btn.setTextColor(resources.getColor(if (isSelect) R.color.switch_thumb_normal_material_dark else R.color.abc_search_url_text_selected))
    }
}