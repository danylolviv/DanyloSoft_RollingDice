package danylosoft.rollingdice

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.setMargins
import java.text.SimpleDateFormat
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

        if (!hM.getList().isEmpty()){
            loadState(mainActivity,diceContainer)
        }
        else {
            for (i in 1..6) {
                val dieNumber = 1
                val imageView = ImageView(mainActivity,)
                imageView.layoutParams = LinearLayout.LayoutParams(150, 150)

                var parLay = imageView.layoutParams as ViewGroup.MarginLayoutParams
                parLay.setMargins(10)

                var resId = drawDice(dieNumber)
                imageView.setImageResource(resId)
                diceContainer.addView(imageView)
            }
        }
    }

    private fun loadState(mainActivity: MainActivity, diceContainer: LinearLayout) {
        val savedState = hM.getList().first().split("-").map { it.toInt() }

        for (i in 1..savedState.size) {
            val dieNumber = savedState[i-1]
            val imageView = ImageView(mainActivity)
            imageView.layoutParams = LinearLayout.LayoutParams(150, 150)

            var parLay = imageView.layoutParams as ViewGroup.MarginLayoutParams
            parLay.setMargins(10)

            var resId = drawDice(dieNumber)
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
        val sdf = SimpleDateFormat("hh:mm:ss")
        val timeStamp = sdf.format(Date())
        this.hM.addEntry(hM.indexCount.toString() + " Result: " + hisString + " Time: " + timeStamp)
    }

    private fun drawDice(dieNumber: Int): Int {
        when (dieNumber) {
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
