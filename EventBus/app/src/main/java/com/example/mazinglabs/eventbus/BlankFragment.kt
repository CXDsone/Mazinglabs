package com.example.mazinglabs.eventbus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*
import org.greenrobot.eventbus.EventBus



class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val eventMessage = MyEvent()
            eventMessage.setMsg(editText.text.toString())
            //传入String
//            val msg = eventMessage.getMsg()
//            EventBus.getDefault().post(msg)
            //传入对象
            EventBus.getDefault().postSticky(eventMessage)
        }
    }


}
