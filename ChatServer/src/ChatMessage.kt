/*
* ChatMessage.kt --
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/

import java.time.LocalTime

class ChatMessage(viesti: String, name: String, time: LocalTime) {
    var Name = name
    var Time = time
    var Message = viesti

    override fun toString(): String {

        return "$Message FROM $Name AT $Time"
    }


}


