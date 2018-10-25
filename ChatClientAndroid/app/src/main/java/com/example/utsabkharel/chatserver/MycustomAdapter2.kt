package com.example.utsabkharel.chatserver

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.users_rows.view.*


class MyCustomAdapter2 (context: Context, list: ArrayList<String>): BaseAdapter() {

    var list: ArrayList<String> = list

    val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: ConstraintLayout

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.users_rows, null) as ConstraintLayout
        } else {
            view = convertView as ConstraintLayout

        }
       // val firstLetter = list[position].get(0).toString()
        //Log.d("firstletter",firstLetter)
        view.item_title.text = list[position]

      view.item_letter.setImageResource(R.drawable.usericonb)

        return view

    }

    override fun getItem(position: Int): Any {
        return list[position]

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    fun add(message: ChatMessage){
        list.add("${message.Message} " )
        notifyDataSetChanged()
    }
}
