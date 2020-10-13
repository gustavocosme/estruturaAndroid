package com.g10.g10rum.utils

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*


class UtilsNotifications {


    companion object{

         fun notification(
            title: String?,
            message: String?,
            context: Context
        ) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = createID()
            val channelId = "channel-id"
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                    channelId, channelName, importance
                )
                notificationManager.createNotificationChannel(mChannel)
            }
            val mBuilder = NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setTicker(message)
                .setSmallIcon(com.gustavocosme.atualizacao.R.mipmap.ic_launcher_round)
                //.setOngoing(true)
                .setVibrate(longArrayOf(100, 250))
                .setLights(Color.YELLOW, 500, 5000)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.background_dark))





             notificationManager.notify(notificationId, mBuilder.build())


         }

        fun createID(): Int {
            val now = Date()
            return SimpleDateFormat("ddHHmmss", Locale.FRENCH).format(now).toInt()
        }

        fun removeAll(ctx:Context)
        {
            val nMgr = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            nMgr!!.cancelAll()

        }

    }

}