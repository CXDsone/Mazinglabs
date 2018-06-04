package com.example.mazinglabs.test

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 方法一：传
         */
        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data",editText.text.toString())
            intent.setClass(this,Main2Activity::class.java)
            //
            //startActivity(intent)
            //方法二必须用这种方式打开下一个界面
            startActivityForResult(intent,1)
        }
    }
    /**
     * 方法二接受
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                editText.setText(data!!.getStringExtra("cxd"))
            }
        }
    }
}
