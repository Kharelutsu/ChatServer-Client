 /*
* Server.kt --
* This is the class where client is connected to server using port number
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/

import java.net.ServerSocket

fun main(args: Array<String>) {
    Server().chatServer()



}

class Server() {
    fun chatServer() {

        ChatConsole.start()
        TopChatters.start()

        val AA = ServerSocket(5544)
        println("We have port: " + AA.localPort)

        while (true) {

            val Random = AA.accept()

            println("We are connected to: " + AA.localPort)

            val obj = Thread ( CommandInterpreter(Random.getInputStream(), Random.getOutputStream(),Random.port))
            obj.start()

        }
    }
}