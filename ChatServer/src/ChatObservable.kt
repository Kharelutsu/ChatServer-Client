/*
* ChatObservable.kt --
*
* Modification(s):
*   First version created on 14th September 2018 by Utsab Kharel (1706249)
*/
interface ChatObservable {

fun registerObserver(observer: ChatObserver)
fun deregisterObserver(observer: ChatObserver)
fun notifyObserver(message:ChatMessage)

}