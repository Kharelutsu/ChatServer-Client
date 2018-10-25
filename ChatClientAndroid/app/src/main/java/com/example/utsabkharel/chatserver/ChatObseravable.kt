package com.example.utsabkharel.chatserver

interface ChatObseravable {

    fun registerObserver(observer: ChatObserver)
    fun deregisterObserver(observer: ChatObserver)
    fun notifyObserver(message:ChatMessage)

}