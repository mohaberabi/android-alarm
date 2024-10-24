package com.mohaberabi.medicinereminder.domain.source

import com.mohaberabi.medicinereminder.domain.model.AppNotificationChannel
import com.mohaberabi.medicinereminder.domain.model.MedicineReminderModel

interface MedicineNotificationManager {
    fun showNotification(
        title: String,
        body: String,
        id: Int,
    )

    fun createAppNotificationsChannels()
}