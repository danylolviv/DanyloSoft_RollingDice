package danylosoft.rollingdice

import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

       val drawHistory = intent.getSerializableExtra("history")

        /*val adapter : ListAdapter  = ArrayAdapter(MutableList<String>)(this,
             android.R.layout.simple_list_item_1,
             drawHistory) */

        //   lvHistory.adapter = adapter
    }
}