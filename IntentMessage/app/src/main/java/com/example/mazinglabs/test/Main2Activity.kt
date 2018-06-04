package com.example.mazinglabs.test

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        /**
         * 方法一：接受
         */
        val intent = intent
        editText2.setText(intent.getStringExtra("data"))
        /**
         * 方法二：传
         */
        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("cxd",editText2.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
