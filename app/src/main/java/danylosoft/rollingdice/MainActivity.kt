package danylosoft.rollingdice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    var diceCount = 6;
    val currentTimestamp = System.currentTimeMillis()
    var historyManager = HistoryManager()
    var diceDrawer = DrawDice(historyManager);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            if (savedInstanceState != null){
                var state = savedInstanceState.getSerializable("historyObj") as HistoryManager
                historyManager = state
                diceDrawer = DrawDice(state)
                diceDrawer.default(this, diceContainer)
            }
            if (savedInstanceState == null) {
                historyManager = HistoryManager()
                diceDrawer = DrawDice(historyManager)
                diceDrawer.default(this, diceContainer)
            }
        }
        catch (e : Exception){
            Log.d("gaming", "I'm dead because $e");
        }


        historyBtn.setOnClickListener{ showHistory()}
        diceRoller.setOnClickListener{ rollDice()}
        autoRoller.setOnClickListener{ autoRoll()}
        numberOfDice.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                diceCount = i+1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun showHistory(){
        val intent = Intent(this, HistoryActivity::class.java)
        intent.putExtra("history",historyManager)
        getResult.launch(intent)

    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                historyManager.resetList()
            }
        }


    private fun rollDice() {
        val numDice = diceCount
        diceContainer.removeAllViews()
        diceDrawer.rollCustom(numDice, diceContainer, this)
        addDist()
    }

    private fun addDist() {
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(20, 20)
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
        Toast.makeText(this, "Happy roll", Toast.LENGTH_LONG).show()
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
        for (i in 1..10){
            //Thread.sleep(500)
            rollDice()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("historyObj", historyManager)
    }


}