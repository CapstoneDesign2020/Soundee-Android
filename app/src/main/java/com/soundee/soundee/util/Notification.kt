package com.soundee.soundee.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.soundee.soundee.MainActivity
import com.soundee.soundee.R

const val CHANNEL_ID="SOUNDEE"
fun setChannel(ctx: Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val name = "SOUNDEE"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = "soundee description"
        }

        val notificationManager: NotificationManager =
            ctx.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

    }
}
fun setAlarm(ctx:Context,contentText:String) {
    setChannel(ctx)
    val pendingIntent = PendingIntent.getActivity(
        ctx,
        0,
        Intent(ctx, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT
    )


    val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
        .setSmallIcon(R.drawable.icon_glass)
        .setContentTitle(CHANNEL_ID)
        .setLargeIcon(BitmapFactory.decodeResource(ctx.resources,R.drawable.icon_baby_crying))
        .setContentText(contentText)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(ctx)) {
        notify(0, builder.build())
    }


}