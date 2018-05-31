package com.example.mazinglabs.eventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus



class MainActivity : AppCompatActivity() {

    var eventBus: EventBus? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        val fm = supportFragmentManager
        val blankFragment = BlankFragment()
        fm.beginTransaction().replace(R.id.frameLayout, blankFragment).commit()

        fun onEvent(event: MyEvent) {
            textView.text = event.getMsg()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
