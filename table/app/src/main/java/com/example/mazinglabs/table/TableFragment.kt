package com.example.mazinglabs.table


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_table.*
import android.widget.SimpleAdapter
import android.widget.Toast

class TableFragment : Fragment() {

    private var dataList: ArrayList<Map<String,Any>>? = null
    private val iconName = arrayOf("通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置", "语音", "天气")
    private var simpleAdapter: SimpleAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun getView(): View? {
        return super.getView()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList = ArrayList<Map<String, Any>>()
        getData();
        simpleAdapter = SimpleAdapter(context, dataList, R.layout.layout, arrayOf("text"), intArrayOf(R.id.textView))
        gridView.adapter = simpleAdapter
        gridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(context,iconName[position],Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData() {
        for (i in 0..8) {
            val map = HashMap<String, Any>()
            map["text"] = iconName[i]
            dataList!!.add(map)
        }
    }
}
