package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }

    }

    private fun clickDatePicker(View : View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->

            val sDate = "$dayOfMonth/${month + 1}/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(sDate)

            selectedDate.text = theDate.toString()

            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val cDateToMin = currentDate!!.time / 60000
            val diff = cDateToMin - selectedDateInMinutes

            ageinmin.text = diff.toString() + " minutes!"

        }, year, month, dayOfMonth)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}