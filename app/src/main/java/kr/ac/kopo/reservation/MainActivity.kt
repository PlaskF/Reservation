package kr.ac.kopo.reservation

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Time

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
    lateinit var btnStart : Button
    lateinit var btnDone : Button
    lateinit var rg : RadioGroup
    lateinit var calender : DatePicker
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView

    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        chrono = findViewById<Chronometer>(R.id.chrono)
        btnStart = findViewById<Button>(R.id.btnStart)
        btnDone = findViewById<Button>(R.id.btnDone)
        rg = findViewById<RadioGroup>(R.id.rg)
        calender = findViewById<DatePicker>(R.id.calender)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById<TextView>(R.id.textResult)

        calender.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListener)
        btnStart.setOnClickListener {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
        }
        btnDone.setOnClickListener{
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            selectedYear = calender.year
            selectedMonth = calender.month
            textResult.setText("" + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 ")
            textResult.append("" + timePick.currentHour + "시 ")
            textResult.append("" + timePick.currentMinute + "분 ")
        }

//        calender.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            selectedYear = year
//            selectedMonth = month + 1
//            selectedDay = dayOfMonth
//        }
    }

    var rgListener = OnCheckedChangeListener { group, checkedId ->
        calender.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId){
            R.id.rbDate -> calender.visibility = View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}