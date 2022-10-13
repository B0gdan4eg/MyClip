package com.shcherbakov_bogdan.myclip.service.smsreceiver

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.shcherbakov_bogdan.myclip.MainActivity


class SmsService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    private fun showNotification(text: String) {
        val contentIntent = PendingIntent.getActivity(
            this, 0, Intent(
                this,
                MainActivity::class.java
            ), 0
        )
        val context: Context = applicationContext
        val builder: Notification.Builder = Notification.Builder(context)
            .setContentTitle("Rugball")
            .setContentText(text)
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setAutoCancel(true)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification = builder.getNotification()
        notificationManager.notify(R.drawable.ic_delete, notification)
    }
}