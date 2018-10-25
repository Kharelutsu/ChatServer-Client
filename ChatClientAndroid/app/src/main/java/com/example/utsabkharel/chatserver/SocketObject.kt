package com.example.utsabkharel.chatserver
import android.util.Log
import java.io.*
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread


object MySocket:  ChatObseravable {

    lateinit var socket: Socket

    val observers = mutableSetOf<ChatObserver>()


    override fun registerObserver(observer: ChatObserver) {
        observers.add(observer)

    }

    override fun deregisterObserver(observer: ChatObserver) {
        observers.remove(observer)

    }

    override fun notifyObserver(message: ChatMessage) {


        for (i in observers) {
            i.updateMessage(message)
        }
    }



     fun run() {

        socket = Socket("10.0.2.2", 5544)


        Log.d("key", "Connected to : ${socket.inetAddress.hostAddress} + ${socket.port}")


        val input = socket.getInputStream()

         val scanner = Scanner(input)

         while (true) {


            try {
                val userInput = scanner.nextLine()

                notifyObserver(seperateMessage(userInput))
            }

            catch (e :Exception) {
                break
            }


        }

    }

    fun send(text: String) {
        val output = PrintStream(socket.getOutputStream(),true)

        thread{ output.println(text)}

    }

   private fun seperateMessage(string: String): ChatMessage {
        var Nameplustime = string.substringAfter("FROM ")
       var Name = Nameplustime.substringBefore(" AT")
        var Time =string.substringAfter("AT")
        var Message = string.substringBefore("FROM")

      return ChatMessage(Message,Name,Time)

    }

}

