package danylosoft.rollingdice

class HistoryManager {

    var historyList = mutableListOf<String>("0","0","0","0","0")

    fun addEntry(str: String){
        historyList.add(0, str)
    }

    fun getList(): MutableList<String>{
        return historyList
    }
}