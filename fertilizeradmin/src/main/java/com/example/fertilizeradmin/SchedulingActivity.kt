package com.example.fertilizeradmin


import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

import com.example.fertilizeradmin.databinding.ActivityMainBinding
import com.example.fertilizeradmin.databinding.ActivitySchedulingBinding

import java.util.*

class SchedulingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySchedulingBinding



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        binding.submitButton.setOnClickListener{ scheduleNotification() }



    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = binding.titleET.text.toString()
        val messageET = binding.messageET
        val message = if (messageET != null) messageET.text.toString() else ""

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }


    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage:"+message+
                        "\nAt: "+dateFormat.format(date)+" "+timeFormat.format(date))
            .setPositiveButton("okay"){_,_ ->}

            .show()



    }

    private fun getTime(): Long {
        val minute  = binding.timePicker.minute
        val hour  = binding.timePicker.hour
        val day  = binding.datePicker.dayOfMonth
        val month  = binding.datePicker.month
        val year  = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year,month,day,hour,minute)
        return calendar.timeInMillis

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif channel"
        val desc ="A description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID,name,importance)
        channel.description=desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}