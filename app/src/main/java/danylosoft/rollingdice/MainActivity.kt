package danylosoft.rollingdice

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    var diceCount = 2;
    var historyManager = HistoryManager()
    var diceDrawer = DrawDice(historyManager);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyManager = HistoryManager()
        diceDrawer = DrawDice(historyManager)
        diceDrawer.default(this, diceContainer )

        diceRoller.setOnClickListener{c -> rollDice()}
        autoRoller.setOnClickListener{a -> autoRoll()}

        numberOfDice.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                diceCount = i
                rollDice()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun rollDice() {
        val numDice = diceCount
        diceContainer.removeAllViews()
        diceDrawer.rollCustom(numDice, diceContainer, this)
        updateHistory()
        addDist()
    }

    private fun updateHistory() {
        historyContainer.removeAllViews()
        val list = historyManager.getList()
        for (i in 0..4){
            diceDrawer.drawHistory(list[i], historyContainer, this)
        }
    }

    private fun addDist() {
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(50, 50)
        var resId = R.drawable.hex
        imageView.setImageResource(resId)
        when (historyManager.doubleCount) {
            2 -> dis2.addView(imageView)
            3 -> dis3.addView(imageView)
            4 -> dis4.addView(imageView)
            5 -> dis5.addView(imageView)
            6 -> dis6.addView(imageView)
            7 -> dis7.addView(imageView)
            8 -> dis8.addView(imageView)
            9 -> dis9.addView(imageView)
            10 -> dis10.addView(imageView)
            11 -> dis11.addView(imageView)
            12 -> dis12.addView(imageView)
        }
    }

    private fun autoRoll() {
        dis2.removeAllViews()
        dis3.removeAllViews()
        dis4.removeAllViews()
        dis5.removeAllViews()
        dis6.removeAllViews()
        dis7.removeAllViews()
        dis8.removeAllViews()
        dis9.removeAllViews()
        dis10.removeAllViews()
        dis11.removeAllViews()
        dis12.removeAllViews()
        for (i in 1..40){
            //delay(2, TimeUnit.MILLISECONDS)
            rollDice()
        }
    }

}