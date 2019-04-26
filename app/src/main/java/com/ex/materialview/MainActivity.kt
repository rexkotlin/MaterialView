package com.ex.materialview

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTextView.setImageView(getDrawable(R.mipmap.download))
        myTextView.setTextView("")
        myTextView.setTextView("叫叫ABC")

        var btnslist : MutableList<String> = mutableListOf()
        btnslist.add("aaaaa")
        btnslist.add("bbbbb")
        btnslist.add("ccccc")
        btnslist.add("ddddd")
        switchttn.setItem(btnslist)

        switchttn_viewgroup.setItem(btnslist)
    }
}
