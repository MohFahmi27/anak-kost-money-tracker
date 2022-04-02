package com.mohfahmi.moneytracker.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.ui.MainActivity

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val userName = inputData.getString(USERNAME)
    private val notificationState = inputData.getBoolean(NOTIFICATION_STATE, false)

    private fun getPendingIntent(): PendingIntent? {
        val intent = Intent(applicationContext, MainActivity::class.java)
        return TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    override fun doWork(): Result {
        return if (notificationState) {
            showNotification()
            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun showNotification() {
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Daily Reminder")
                .setContentIntent(getPendingIntent())
                .setContentText(
                    applicationContext.getString(
                        R.string.notify_content, userName
                    )
                )
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                applicationContext.getString(R.string.notify_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }

    companion object {
        const val USERNAME = "username_tag"
        const val NOTIFICATION_STATE = "notification_tag"
        const val NOTIFICATION_CHANNEL_ID = "notify-channel"
    }
}