package com.mohaberabi.medicinereminder

import android.app.Application
import com.mohaberabi.medicinereminder.data.di.AppModule
import com.mohaberabi.medicinereminder.data.di.DefaultAppModule

class MedicineReminderApp : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = DefaultAppModule(this).also {
            it.reminderNotificationManager.createAppNotificationsChannels()
        }
    }
}