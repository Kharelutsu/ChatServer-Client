/*
* User.kt --
* This class stores the all the users which can be fetched from commandInterpreter
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/
import java.util.*

 object Users {
    val myUsers: MutableMap<Int,String> = mutableMapOf()

    fun addUser(port:Int, nam: String):String {
        if (myUsers.containsKey(port)) {
            myUsers.replace(port, nam)
        } else {
            myUsers.put(port, nam)
        }
        return nam
    }
     fun removeName(port: Int, nam: String) {
         myUsers.remove(port, nam)
     }


    override fun toString(): String {
        var usr:String = ""
        for(j in myUsers )
            usr += "$j\n"
        return usr
    }

}

