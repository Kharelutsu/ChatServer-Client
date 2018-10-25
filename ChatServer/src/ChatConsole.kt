/*
* ChatConsole.kt --
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/
object ChatConsole :ChatObserver{
    fun start() {
        ChatHistory.registerObserver(this)
    }

    override fun newMessage(message: ChatMessage) {
        println(message)
    }

}