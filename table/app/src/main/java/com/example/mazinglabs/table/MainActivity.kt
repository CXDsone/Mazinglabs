package com.example.mazinglabs.table

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = BlankFragment()
        replaceFragment(fragment,R.id.fl_main)
        button2.isFocusable = false
        button3.isFocusable = false
        button.isFocusable = false
        button4.isFocusable = false
        button5.isFocusable = false
        button6.isFocusable = false
        button7.isFocusable = false
        button8.isFocusable = false
        button9.isFocusable = false

        button.setOnClickListener {
            button.isFocusable = true
            button.requestFocus()
            button.requestFocusFromTouch()
        }

        button2.setOnClickListener { view ->
            view.isFocusable = true
            view.requestFocus()
            view.requestFocusFromTouch()
        }

        button3.setOnClickListener {
            button3.isFocusable = true
            button3.requestFocus()
            button3.requestFocusFromTouch()
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction {
            replace(frameId, fragment)
        }
    }
}
