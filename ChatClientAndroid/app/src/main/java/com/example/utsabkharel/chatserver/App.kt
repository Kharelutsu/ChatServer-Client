package com.example.utsabkharel.chatserver

import android.app.Application

class ChatUser:Application() {
    companion object {
        lateinit var user:String
    }
}