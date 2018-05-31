package com.example.mazinglabs.eventbus

class MyEvent {

    private var message: String ?= null

    fun EventUtil(msg: String){
        message = msg
    }

    fun getMsg(): String {
        return message!!
    }
}