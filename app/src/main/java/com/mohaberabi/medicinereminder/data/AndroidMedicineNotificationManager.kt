package com.mohaberabi.medicinereminder.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.mohaberabi.medicinereminder.R
import com.mohaberabi.medicinereminder.domain.model.AppNotificationChannel
import com.mohaberabi.medicinereminder.domain.model.MedicineReminderModel
import com.mohaberabi.medicinereminder.domain.source.MedicineNotificationManager
import com.mohaberabi.medicinereminder.domain.util.isNotificationGranted

class AndroidMedicineNotificationManager(
    private val context: Context,
) : MedicineNotificationManager {
    private val notificationManager by lazy {
        context.getSystemService<NotificationManager>()!!
    }

    override fun showNotification(
        title: String,
        body: String,
        id: Int,
    ) {
        val notification = NotificationCompat.Builder(context, AppNotificationChannel.Default.id)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        if (context.isNotificationGranted()) {
            notificationManager.notify(id, notification)
        }
    }

    override fun createAppNotificationsChannels(
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = AppNotificationChannel.entries.map {
                NotificationChannel(
                    it.id,
                    it.label,
                    it.importance
                )
            }
            notificationManager.createNotificationChannels(channels)
        }

    }


}