package com.mohaberabi.medicinereminder.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mohaberabi.medicinereminder.MedicineReminderApp
import com.mohaberabi.medicinereminder.data.di.DefaultAppModule
import com.mohaberabi.medicinereminder.domain.util.AppConst

class MedicineReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { ctx ->
            val notificationManager = MedicineReminderApp.appModule.reminderNotificationManager
            intent?.let { int ->
                val title = int.getStringExtra(AppConst.REMINDER_TTL_KEY) ?: "not known "
                val body = int.getStringExtra(AppConst.REMINDER_BODY_KEY) ?: "not known"
                notificationManager.showNotification(
                    title,
                    body,
                    2,
                )
            }
        }
    }
}