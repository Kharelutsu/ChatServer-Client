/*
* CommandInterpreter.kt --
* Class where it detects the user input and sends the data to the different class
* It also prints out the data by extracting from the classes
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/

import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class CommandInterpreter(input:InputStream, output: OutputStream, Port:Int) :Runnable, ChatObserver {
    var scanner = Scanner(input)
    var printer = PrintStream(output, true)
    var port = Port

    var quit: Boolean = false
    val commands = listOf(":user",":users",":history",":quit")
    val samaye  = LocalTime.now()

    var userName: String = ""
    var userCommand:String=""
    var userInput: String = ""

    init {
        ChatHistory.registerObserver(this)
    }

    override fun newMessage(message: ChatMessage) {
        printer.println(message)
    }

    override fun run() {
        //printer.println("Please register the username")

        while (!quit) {
            userInput = scanner.nextLine()

            if (userInput.startsWith(":")) {
                var splitInput = userInput.split(' ')// Or userInput.substringAfter(":user")
                userCommand = splitInput.getOrNull(0) ?: " "


                if (commands.contains(userCommand)) {

                    if (userCommand.startsWith(":user") && !userCommand.startsWith(":users")) {
                        userName = splitInput.getOrNull(1) ?: " "
                            Users.addUser(port,userName)
                            printer.println("Username is set to " + userName)
                        }


                    when (userCommand) {
                        ":quit" -> {
                            Users.removeName(port,userName)
                            ChatHistory.deregisterObserver(this)
                           // printer.println("Goodbye!")
                            quit = true
                        }

                        ":users" -> {
                            //printer.println("Active Users: \n" + Users.toString())
                            //myUsers.forEach({ printer.println(it)})

                            for (user in Users.myUsers) {
                            printer.println(user.value)
                            }
                        }

                        ":history" -> {
                            printer.println(ChatHistory.toString())
                        }
                    }


                } else {
                    printer.println("Didn't get it")
                }


            } else {
                if (Users.myUsers.containsValue(userName)) {

                    var textMessage = ChatMessage(userInput, userName, samaye)
                    ChatHistory.insert(textMessage)

                } else {
                    printer.println("Username not set, Register the name first")

                }
            }



        }
        scanner.close()
        printer.close()
    }
}













