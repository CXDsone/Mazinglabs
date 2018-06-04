package com.example.mazinglabs.eventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import android.R.attr.name
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!EventBus.getDefault().hasSubscriberForEvent(MainActivity::class.java)) {
            EventBus.getDefault().register(this)
            Log.e("kotlin==注册啦","")
        }

        val fm = supportFragmentManager
        val blankFragment = BlankFragment()
        fm.beginTransaction().replace(R.id.frameLayout, blankFragment).commit()
    }

    /**
     * 注意传递的参数
     *      如果传递的是对象，这里也要传入对象
     *      如果传递的是字符串，这里要传入String
     *      以此类推...
     */
//
//    @Subscribe
//    fun onEvent(string: String) {
//        textView.text = string
//    }

    @Subscribe
    fun onEvent(event: MyEvent){
        textView.text = event.getMsg()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
