package com.example.pesticide

import java.util.*

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.*


class CalenderActivity : AppCompatActivity() {
    private var calendarView: CalendarView? = null
    private var editText: EditText? = null
    private var displayEvent: TextView? = null
    private var stringDateSelected: String? = null
    private var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /*supportActionBar?.setDisplayShowHomeEnabled(true)*/

        calendarView = findViewById<CalendarView>(R.id.calendarView)
        editText = findViewById<EditText>(R.id.editText)
        displayEvent = findViewById<TextView>(R.id.displayEvent)


        calendarView?.setOnDateChangeListener(OnDateChangeListener { calendarView, year, month, dayOfMonth ->
            /*stringDateSelected =
                Integer.toString(i) + Integer.toString(i1 + 1) + Integer.toString(i2)*/
            stringDateSelected = "$year-${month + 1}-$dayOfMonth"
            calendarClicked()
        })
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        displayEvent!!.setText(snapshot.value.toString())
                    } else {
                        displayEvent!!.setText("no event")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }




    fun buttonSaveEvent(view: View?) {
        val event = editText!!.text.toString()
        databaseReference!!.child(stringDateSelected!!).setValue(event)
        Toast.makeText(this, "Event saved for $stringDateSelected", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}






/*import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class CalenderActivity : AppCompatActivity() {
    private var calendarView: CalendarView? = null
    private var editText: EditText? = null
    private var stringDateSelected: String? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)
        calendarView = findViewById<CalendarView>(R.id.calendarView)
        editText = findViewById<EditText>(R.id.editText)
        calendarView?.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
            /*stringDateSelected =
                Integer.toString(i) + Integer.toString(i1 + 1) + Integer.toString(i2)*/
            stringDateSelected = "$i${i1 + 1}$i2"
            calendarClicked()
        })
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        editText!!.setText(snapshot.value.toString())
                    } else {
                        editText!!.setText("null")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        // Highlight the selected date
        val calendar = Calendar.getInstance()
        calendar.set(stringDateSelected!!.substring(0, 4).toInt(), stringDateSelected!!.substring(4, 6).toInt() - 1, stringDateSelected!!.substring(6).toInt())
        val dateInMillis = calendar.timeInMillis
        calendarView?.date = dateInMillis
        calendarView?.setDate(dateInMillis, true, true)
        calendarView?.setFocusedMonthDateColor(Color.RED)
    }

    fun buttonSaveEvent(view: View?) {
        databaseReference!!.child(stringDateSelected!!).setValue(editText!!.text.toString())
    }
}*/
