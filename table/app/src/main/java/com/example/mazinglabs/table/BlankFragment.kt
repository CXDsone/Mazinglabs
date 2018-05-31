package com.example.mazinglabs.table


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import kotlinx.android.synthetic.main.fragment_blank.*




class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNum(4)
    }

    fun addNum(num: Int){
        tableLayout.removeAllViews()
        val params = TableRow.LayoutParams(55, 55)
        for(i in 1..num){
            val tableRow = TableRow(context)
            for(j in 1..num){
                val button = Button(context)
                button.layoutParams = params
                button.isFocusable = false
                button.id = (i-1)*num+j
                button.text = "水溶液"
                button.textSize = 12F
                button.setTextColor(android.graphics.Color.WHITE)
                button.setBackgroundResource(R.drawable.btn_style)
                button.setOnClickListener { view->
                    view.isFocusable = true
                    view.requestFocus()
                    view.requestFocusFromTouch()
                    var row = button.id.toString()
                }
                tableRow.addView(button)
            }
            tableLayout.addView(tableRow)
        }
    }
}
