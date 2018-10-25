package com.example.utsabkharel.chatserver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.login_page.*
import kotlin.concurrent.thread

class  LoginActivity : AppCompatActivity(),ChatObserver {

    var userName = ""


    override fun updateMessage(message: ChatMessage) {

        if (message.Message == "Username is set to $userName") {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()// so that it wont take back to the login screen
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        MySocket.registerObserver(this)
        thread { MySocket.run() }

    }
     fun onClick (v:View){
         if (userNameEditText.text.isNotEmpty()) {

             userName = userNameEditText.text.toString()

             ChatUser.user = userName

             val command = ":user ${userName}"
             userNameEditText.text.clear()
             MySocket.send(command)
         }
     }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Login_btn.performClick()
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }
}

