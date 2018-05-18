package com.example.mazinglabs.title_toolbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.WindowManager

class titleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val ct = CustomTitleBar()
//        ct.getTitleBar(this, "人员管理")
        val bar = supportActionBar as ActionBar
        bar.setDisplayShowCustomEnabled(true)
        val v = LayoutInflater.from(applicationContext).inflate(R.layout.title,null)
        bar.setCustomView(v, ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT))
        setContentView(R.layout.activity_title)
    }
}
