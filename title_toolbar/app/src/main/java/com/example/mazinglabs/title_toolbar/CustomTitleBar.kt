package com.example.mazinglabs.title_toolbar

import android.app.ActionBar
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import android.view.Window.FEATURE_CUSTOM_TITLE
import android.widget.Toast


/**
 * Created by Mazinglabs on 2018/5/18.
 */
class CustomTitleBar {
    private var mActivity: Activity? = null

    fun getTitleBar(activity: Activity, title: String) {
        mActivity = activity
        activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        activity.setContentView(R.layout.title)
        val textView = activity.findViewById<View>(R.id.mytitle) as TextView
        textView.text = title
        val titleBackBtn = activity.findViewById<View>(R.id.bt_back) as ImageButton
        titleBackBtn.setOnClickListener {
            mActivity!!.onBackPressed()
        }

        val titleAddBtn = activity.findViewById<View>(R.id.bt_add) as ImageButton
        titleAddBtn.setOnClickListener {
            Toast.makeText(this.mActivity,"添加用户",Toast.LENGTH_SHORT)
        }
    }

}