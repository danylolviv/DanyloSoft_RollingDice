package danylosoft.rollingdice

import java.io.Serializable

class HistoryManager : Serializable{

    var historyList = mutableListOf<String>()
    public var doubleCount = 0

    fun addEntry(str: String){
        historyList.add(0, str)
    }

    fun getList(): MutableList<String>{
        return historyList
    }

    fun resetList(){
        historyList = mutableListOf()
    }
}