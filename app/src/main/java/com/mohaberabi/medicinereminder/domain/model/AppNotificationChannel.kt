package com.mohaberabi.medicinereminder.domain.model

import android.app.NotificationManager


enum class AppNotificationChannel(
    val label: String,
    val id: String,
    val importance: Int,
) {
    Default(
        "default",
        id = "default",
        importance = NotificationManager.IMPORTANCE_DEFAULT
    )

}