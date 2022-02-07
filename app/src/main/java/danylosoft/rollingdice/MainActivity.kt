package danylosoft.rollingdice

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


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
    }

    private fun updateHistory() {
        historyContainer.removeAllViews()
        val list = historyManager.getList()
        for (i in 0..4){
            val textView = TextView(this)
            textView.text = list[i]
            historyContainer.addView(textView)
            diceDrawer.drawHistory(list[i], historyContainer, this)
        }
    }

}