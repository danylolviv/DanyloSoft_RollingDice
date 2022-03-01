package danylosoft.rollingdice

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.setMargins
import java.util.*


class DrawDice {

    val imgResId1 = R.drawable.dice1
    val imgResId2 = R.drawable.dice2
    val imgResId3 = R.drawable.dice3
    val imgResId4 = R.drawable.dice4
    val imgResId5 = R.drawable.dice5
    val imgResId6 = R.drawable.dice6

    val ran: Random
    val hM: HistoryManager

    constructor(historyManager: HistoryManager) {
        this.hM = historyManager
        this.ran = Random()
    }

    fun default(mainActivity: MainActivity, diceContainer: LinearLayout) {
        for (i in 1..2) {
            val b = ran.nextInt(6)
            val random = b + 1
            val imageView = ImageView(mainActivity,  )
            imageView.layoutParams = LinearLayout.LayoutParams(150, 150)

            var parLay = imageView.layoutParams as ViewGroup.MarginLayoutParams
            parLay.setMargins(10)

            var resId = drawDice(random)
            imageView.setImageResource(resId)
            diceContainer.addView(imageView)
        }
    }

    fun rollCustom(childCount: Int, diceContainer: LinearLayout, mainActivity: MainActivity) {
        var hisString = ""
        hM.doubleCount = 0
        for (i in 1..childCount) {

            val b = ran.nextInt(6)
            val random = b + 1

            if (childCount == 2){
                hM.doubleCount += random
            }

            hisString += random
            if (i != childCount) hisString += "-"

            val imageView = ImageView(mainActivity)
            imageView.layoutParams = LinearLayout.LayoutParams(150, 150)

            var parLay = imageView.layoutParams as ViewGroup.MarginLayoutParams
            parLay.setMargins(10)

            var resId = drawDice(random)
            imageView.setImageResource(resId)
            diceContainer.addView(imageView)
        }
        this.hM.addEntry(hisString)
    }

    fun addHistory(sequence: String, historyContainer: LinearLayout, mainActivity: MainActivity) {
        //hM.addEntry(sequence.removeSurrounding("[", "]").split(","))

        // Text solution
        //val rollHistory = TextView(mainActivity)
        //rollHistory.text = sequence


        //        val intList = sequence
//            .removeSurrounding("[", "]")
//            .split(",")
//            .map { it.toInt() }
    }

    private fun drawDice(random: Int): Int {
        when (random) {
            1 -> return imgResId1
            2 -> return imgResId2
            3 -> return imgResId3
            4 -> return imgResId4
            5 -> return imgResId5
            6 -> return imgResId6
            else -> return 0
        }
    }
}
