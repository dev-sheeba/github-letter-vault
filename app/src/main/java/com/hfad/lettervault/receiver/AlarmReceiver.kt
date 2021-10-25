package com.hfad.lettervault.receiver

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import android.app.NotificationChannel
import android.os.Build
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.hfad.lettervault.R
import com.hfad.lettervault.notification.sendNotification


class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            context.getText(R.string.letter_ready).toString(),
            context
        )
    }
}




//class MyNotificationPublisher : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification = intent.getParcelableExtra<Notification>(NOTIFICATION)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val notificationChannel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                "NOTIFICATION_CHANNEL_NAME",
//                importance
//            )
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        val id = intent.getIntExtra(NOTIFICATION_ID, 0)
//        notificationManager.notify(id, notification)
//    }
//
//    companion object {
//        var NOTIFICATION_ID = "notification-id"
//        var NOTIFICATION = "notification"
//        var NOTIFICATION_CHANNEL_ID = "letter_channel"
//    }
//}