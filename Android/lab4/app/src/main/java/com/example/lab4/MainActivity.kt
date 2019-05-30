package com.example.lab4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.Intent
import android.widget.CalendarView
import android.R.attr.button
import android.content.Context
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.app.TimePickerDialog




class MainActivity : AppCompatActivity()
{
    companion object {
        var PREFERENCES = "PREFERENCES"
        var WIDGET_DATE = "widget_date"
        val calendar: Calendar = GregorianCalendar.getInstance()
    }

    private var widget_date: Long = -1

    private var _year = -1
    private var _month = -1
    private var _dayOfmonth = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        widget_date = sharedPreferences.getLong(WIDGET_DATE, -1)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            _year = year
            _month = month
            _dayOfmonth = dayOfMonth
        }

        if (widget_date > 0) {
            calendarView.date = widget_date
        }

        button2.setOnClickListener {
            val intent = Intent(applicationContext, WidgetProvider::class.java)
            intent.action = resources.getString(R.string.intent_action)
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

            calendar.set(_year, _month, _dayOfmonth, 12, 0)
            TimePickerDialog(
                this@MainActivity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    sharedPreferences.edit().putLong(WIDGET_DATE, calendar.timeInMillis).apply()
                    sendBroadcast(intent)
                    finish()
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true)
                .show()
        }
    }
}
