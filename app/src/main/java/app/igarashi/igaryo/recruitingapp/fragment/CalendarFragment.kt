package app.igarashi.igaryo.recruitingapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import app.igarashi.igaryo.recruitingapp.R

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendar:CalendarView = root.findViewById(R.id.calendarView)
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            root.findViewById<TextView>(R.id.textView).text = "" + year + "-" + (month+1) + "-" + dayOfMonth
        }
        return root
    }
}