package com.food_dev.merchant.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.food_dev.utils.R
import com.food_dev.utils.base.navigation.sendActivityIntent
import com.google.firebase.messaging.FirebaseMessagingService

class NotificationService {

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setNotification(context: Context, data: MutableMap<String, String>){

        val intent              = sendActivityIntent(context, "com.food_dev.dashboard.activity.ui.MainActivity").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val vibrate             = longArrayOf(100, 200, 300, 400)
        val pendingIntent       = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val channelId           = context.getString(R.string.default_notification_channel_id)
        val defaultSoundUri     = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(data["title"])
            .setContentText(data["text"])
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.ic_logo_desk_bell_24)
            .setAutoCancel(true)
            .setVibrate(vibrate)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentIntent(pendingIntent)

        val buildNotification: Notification = notificationBuilder.build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val idChannel           = context.getString(R.string.default_notification_channel_id)
            val channelName         = "sosChannel"
            val notifManager        = context.getSystemService(NotificationManager::class.java)
            val uriDefaults         = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val importance          = NotificationManager.IMPORTANCE_HIGH
            val channel             = NotificationChannel(idChannel, channelName, importance)
            val att                 = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build()

            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor           = Color.RED
            channel.vibrationPattern     = vibrate
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel.setShowBadge(true)
            channel.setSound(uriDefaults, att)

            if (notifManager != null) {
                notifManager.createNotificationChannel(channel)
                notifManager.notify(0, buildNotification)
            }
        } else {
            val notifyManager = context.getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager
            notifyManager.notify(0, buildNotification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerOtherChannel( context: Context, channelName: String, importance: Int){
        val notifManager        = context.getSystemService(NotificationManager::class.java)
        val currentChannel      = notifManager.getNotificationChannel(context.getString(R.string.default_notification_channel_id2))

        if (currentChannel == null){
            val idChannel           = context.getString(R.string.default_notification_channel_id2)
            val vibrate             = longArrayOf(100, 200, 300, 400)
            val uriDefaults         = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val channel             = NotificationChannel(idChannel, channelName, importance)
            val att                 = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).setContentType(
                AudioAttributes.CONTENT_TYPE_SPEECH).build()

            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor           = Color.RED
            channel.vibrationPattern     = vibrate
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel.setShowBadge(true)
            channel.setSound(uriDefaults, att)
            notifManager.createNotificationChannel(channel)
        }
    }
}