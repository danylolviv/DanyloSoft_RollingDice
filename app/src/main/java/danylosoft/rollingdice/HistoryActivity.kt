package danylosoft.rollingdice

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        returnBtn.setOnClickListener{ onBackPressed()}

       val history = intent.getSerializableExtra("history") as? HistoryManager


        val adapter = HistoryAdapter(this, history!!.getList())

           lvHistory.adapter = adapter
    }

    internal class HistoryAdapter(context: Context,
                                 private val rolls: MutableList<String>
    ) : ArrayAdapter<String>(context, 0, rolls)
    {
        // these colors are used to toogle the background of the list items.
        private val colours = intArrayOf(
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#CCCCCC")
        )

        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.sample_cell_history, null)

            }
            val resView: View = v1!!
            resView.setBackgroundColor(colours[position % colours.size])
            val f = rolls[position]
            val textView = resView.findViewById<TextView>(R.id.tvHistory)
            textView.text = f

            return resView
        }
    }
}