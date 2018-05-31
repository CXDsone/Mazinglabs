package com.example.mazinglabs.eventbus

import android.util.Log

class MyEvent {

    private var message: String ?= null

    fun setMsg(msg: String){
        Log.d("event" ,msg)
        message = msg
    }

    fun getMsg(): String {
        return message!!
    }
}