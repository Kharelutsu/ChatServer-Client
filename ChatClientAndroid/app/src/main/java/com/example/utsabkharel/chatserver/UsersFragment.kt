package com.example.utsabkharel.chatserver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class UsersFragment : Fragment(),ChatObserver  {
    val Users = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val userView = inflater.inflate(R.layout.fragment_users, container, false)

        val myAdapter = MyCustomAdapter2(context as AppCompatActivity,Users)
        val myUsersList = userView.findViewById<ListView>(R.id.list_view)
        myUsersList.adapter = myAdapter
       return userView
    }

    override fun onStart() {
        super.onStart()
        MySocket.send(":users")
        MySocket.registerObserver(this)
    }

    override fun onPause() {
        super.onPause()
        MySocket.deregisterObserver(this)
    }
    override fun updateMessage(message: ChatMessage) {
        Log.d("UsersFragment","message: ${message.Message}")
       // var user = message.Message.substringAfter("FROM").substringBefore("AT").trim()
       var user = message.Message

        Log.d("UsersFragment","user: ${user}")
        Users.add(user)
    }
}
