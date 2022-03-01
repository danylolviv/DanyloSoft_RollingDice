package danylosoft.rollingdice

import java.io.Serializable

class HistoryManager : Serializable{

    var historyList = mutableListOf<String>("0","0","0","0","0")
    public var doubleCount = 0

    fun addEntry(str: String){
        historyList.add(0, str)
    }

    fun getList(): MutableList<String>{
        return historyList
    }
}