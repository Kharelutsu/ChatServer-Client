/*
* TopChatters.kt --
*
* Class to find out no of messages enter by users and also print out the top four
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/
object TopChatters:ChatObserver {
    fun start() {
        ChatHistory.registerObserver(this)
    }

    override fun newMessage(message: ChatMessage) {
        val yy = Users.myUsers.map { user -> user to ChatHistory.chatHistory

                .count { message -> message.Name == user.value }
        }
                .sortedByDescending { it.second }.take(4)

                .fold("Users:") { string, input -> "$string \n ${ input.first }  ${ input.second +1}" }


        println(yy)

    }
}