package com.mohaberabi.medicinereminder.data.di

import android.content.Context
import com.mohaberabi.medicinereminder.data.AndroidMedicineAlarmManager
import com.mohaberabi.medicinereminder.data.AndroidMedicineNotificationManager
import com.mohaberabi.medicinereminder.domain.source.MedicineAlarmManager
import com.mohaberabi.medicinereminder.domain.source.MedicineNotificationManager

interface AppModule {
    val reminderAlarmManager: MedicineAlarmManager
    val reminderNotificationManager: MedicineNotificationManager
}


class DefaultAppModule(
    private val context: Context,
) : AppModule {
    override val reminderAlarmManager: MedicineAlarmManager
            by lazy {
                AndroidMedicineAlarmManager(context)
            }
    override val reminderNotificationManager: MedicineNotificationManager
            by lazy {
                AndroidMedicineNotificationManager(context)
            }
}