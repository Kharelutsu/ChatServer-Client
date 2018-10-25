package com.example.utsabkharel.chatserver

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_chat.*


 class ChatFragment : Fragment(), ChatObserver{

     companion object {
         private const val USER_MESSAGES_KEY = "messages"
     }

   private lateinit var myAdapter: MyCustomAdapter


    var myMessages = arrayListOf<ChatMessage>()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("@check","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("@check","onCreate")

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val xyView = inflater.inflate(R.layout.fragment_chat, container, false)
        Log.d("@check","onCreateView")

        val editText = xyView.findViewById<EditText>(R.id.edit_Text)
        val myButton = xyView.findViewById<Button>(R.id.my_Button)

       // myMessages = MySocket.allMessages

        val myMessageList = xyView.findViewById<RecyclerView>(R.id.messageList)

       myMessageList.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        //myMessages = newArrayList
        myAdapter = MyCustomAdapter(context as AppCompatActivity, myMessages)

      myMessageList.adapter = myAdapter

        myButton.setOnClickListener {

            val message = editText.text.toString()
            if (!edit_Text.text.isEmpty()) {

                MySocket.send(message)

                Log.d("key", "sent the message")


                editText.text.clear()


            }
        }

        return xyView

    }

   override fun onStart() {
        super.onStart()
       Log.d("@check","onStart")

       MySocket.registerObserver(this)
    }
    override fun onResume() {
        super.onResume()
        Log.d("@check","onResume:")
    }


     override fun onPause() {
        super.onPause()
         MySocket.deregisterObserver(this)
         Log.d("@check","onPaused")
    }


    override fun onStop() {
        super.onStop()
        Log.d("@check","onStop")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("@check","onDestroyView")

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("@check","onDestroy")

    }

     override fun updateMessage(message: ChatMessage) {
         activity?.runOnUiThread {
             myAdapter.addMessage(message)
             messageList.scrollToPosition(myAdapter.itemCount - 1);

         }
     }
         /*  override fun onSaveInstanceState(outState: Bundle) {
               super.onSaveInstanceState(outState)
          //outState.get(USER_MESSAGES_KEY,myMessages)
               for(i in myMessages) {
                   outState.putString(USER_MESSAGES_KEY, i.toString())
               }
       }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
          myMessages =savedInstanceState.getParcelableArrayList<ChatMessage>(USER_MESSAGES_KEY)

     }*/







}
