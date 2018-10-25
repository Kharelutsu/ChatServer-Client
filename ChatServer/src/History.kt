/*
* History.kt --
* It saves all the messages input by user
* Also saves observers and remove observers
* It has observers who will be notified whenever there is a message
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/
object ChatHistory : ChatObservable {
    val observers = mutableListOf<ChatObserver>()

    val chatHistory = mutableListOf<ChatMessage>()

    fun insert(sandesh: ChatMessage) {
        notifyObserver(sandesh)
        chatHistory.add(sandesh)

    }

    override fun toString(): String {

        var text: String = ""

        for (i in chatHistory)

            text += "$i \n"

        return text

    }


    override fun registerObserver(observer: ChatObserver) {
        observers.add(observer)
    }

    override fun deregisterObserver(observer: ChatObserver) {
        observers.remove(observer)
    }


    override fun notifyObserver(message: ChatMessage) {
        for (i in observers) {

            i.newMessage(message)

        }

    }
}








