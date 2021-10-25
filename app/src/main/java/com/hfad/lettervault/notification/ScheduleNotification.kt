package com.hfad.lettervault.notification

import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService


import android.app.*

import android.app.DatePickerDialog.OnDateSetListener

import androidx.core.content.ContextCompat.getSystemService


import android.content.Intent
import android.graphics.BitmapFactory

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.navDeepLink
import com.hfad.lettervault.MainActivity
import com.hfad.lettervault.R
import com.hfad.lettervault.receiver.SnoozeReceiver


private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val letterImage =
        BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_letter_sprinkles)

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(letterImage)
        .bigLargeIcon(null)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.letter_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_notifications)
        .setContentTitle("Letter vault")
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(letterImage)
        .addAction(
            R.drawable.ic_letter_sprinkles,
            applicationContext.getString(R.string.snooze),
            snoozePendingIntent
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())

}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}

//private const val channelId = "Default"
//object Notifier {
//
//    fun init(activity: Activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationManager =
//                activity.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
//            val existingChannel = notificationManager.getNotificationChannel(channelId)
//            if (existingChannel == null) {
//                val name = activity.getString(R.string.letter_notification_channel_name)
//                val importance = NotificationManager.IMPORTANCE_DEFAULT
//                val mChannel = NotificationChannel(channelId, name, importance)
//                mChannel.description = activity.getString(R.string.letter_notification_description)
//            }
//        }
//    }
//
//    fun sendNotification(id: Long, context: Context, intent: PendingIntent) {
//        val builder = NotificationCompat.Builder(context, channelId )
//        builder.setContentTitle(context.getString(R.string.deepLinkNotificationTitle))
//            .setSmallIcon(R.drawable.ic_letter_sprinkles)
//        val text = context.getString(R.string.addLetterInfo)
//        val notification = builder.setContentText(text)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(intent)
//            .setAutoCancel(true)
//            .build()
//        val notificationManager = NotificationManagerCompat.from(context)
//        // Remove prior notifications; only allow one at a time to edit the latest item
//        notificationManager.cancelAll()
//        notificationManager.notify(id.toInt(), notification)
//    }
//}


//class MainActivity : AppCompatActivity() {
//    var btnDate: Button? = null
//    val myCalendar: Calendar = Calendar.getInstance()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        btnDate = findViewById(R.id.btnDate)
//    }
//
//    private fun scheduleNotification(notification: Notification, delay: Long) {
//        val notificationIntent = Intent(this, MyNotificationPublisher::class.java)
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1)
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification)
//        val pendingIntent = PendingIntent.getBroadcast(
//            this,
//            0,
//            notificationIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        val alarmManager = (getSystemService(ALARM_SERVICE) as AlarmManager)
//        alarmManager[AlarmManager.ELAPSED_REALTIME_WAKEUP, delay] = pendingIntent
//    }
//
//    private fun getNotification(content: String): Notification {
//        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
//        builder.setContentTitle("Scheduled Notification")
//        builder.setContentText(content)
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
//        builder.setAutoCancel(true)
//        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
//        return builder.build()
//    }
//
//    var date =
//        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            myCalendar.set(Calendar.YEAR, year)
//            myCalendar.set(Calendar.MONTH, monthOfYear)
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateLabel()
//        }
//
//    fun setDate(view: View?) {
//        DatePickerDialog(
//            this@MainActivity, date,
//            myCalendar.get(Calendar.YEAR),
//            myCalendar.get(Calendar.MONTH),
//            myCalendar.get(Calendar.DAY_OF_MONTH)
//        ).show()
//    }
//
//    private fun updateLabel() {
//        val myFormat = "dd/MM/yy" //In which you need put here
//        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
//        val date: Date = myCalendar.getTime()
//        btnDate.setText(sdf.format(date))
//        scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime())
//    }
//
//    companion object {
//        const val NOTIFICATION_CHANNEL_ID = "10001"
//        private const val default_notification_channel_id = "default"
//    }
//}



