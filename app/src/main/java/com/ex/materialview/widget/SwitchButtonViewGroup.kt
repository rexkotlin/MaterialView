package com.ex.materialview.widget

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Button
import com.ex.materialview.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SwitchButtonViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(
    context,
    attrs,
    defStyleAttr
) {

    var width:Int? = null
    var height:Int? = null
    val data:MutableList<String> = mutableListOf()
    private var selectData:String? = null

    private val onItemSelectCallBack: PublishSubject<Int> = PublishSubject.create<Int>()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        data.forEach {
            val button = Button(context)
            button.text = it
            button.background = resources.getDrawable(R.mipmap.download)
            setBtnTextColor(button, false)
            button.setOnClickListener {
                for (index in 0 until childCount) {
                    if (getChildAt(index) == (it as Button)) {
                        selectData = data[index]
                        onItemSelectCallBack.onNext(index)
                    }
                }
                updataItemStatus()
            }

            addView(button)
        }

        val childWidth = width!! / data.size

        for (index in 0 until childCount) {
            val child = getChildAt(index)
            val makeMeasureSpecWidth = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY)
            val makeMeasureSpecHeight = MeasureSpec.makeMeasureSpec(height!!, MeasureSpec.EXACTLY)
            child.measure(makeMeasureSpecWidth, makeMeasureSpecHeight)
            child.layout(childWidth * index, 0, child.measuredWidth * (index + 1), child.measuredHeight)
        }
        updataItemStatus()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        width = MeasureSpec.getSize(widthMeasureSpec)
        height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
        background = resources.getDrawable(R.drawable.shape_switch_btn_bg)
    }

    fun getSelectCallBack(): Observable<Int> {
        return onItemSelectCallBack
    }

    private fun setBtnTextColor(btn: Button, isSelect: Boolean) {
        resources.getColor(R.color.switch_thumb_normal_material_dark)
        btn.setTextColor(resources.getColor(if (isSelect) R.color.switch_thumb_normal_material_dark else R.color.abc_search_url_text_selected))
    }

    fun setItem(data: List<String>) {
        this.data.clear()
        this.data.addAll(data)
        selectData = data[0]
    }

    fun setSelectItem(supportCountry: String) {
        selectData = supportCountry
    }

    private fun updataItemStatus() {
        data.forEachIndexed { index, data ->
            val child = getChildAt(index)
            val button = child as Button
            if (selectData == data) {
                setBtnTextColor(button, true)
                button.isEnabled = false
            } else {
                button.isEnabled = true
                setBtnTextColor(button, false)
            }
        }
    }

}