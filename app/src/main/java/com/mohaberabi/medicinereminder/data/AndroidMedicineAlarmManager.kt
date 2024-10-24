package com.mohaberabi.medicinereminder.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.getSystemService
import com.mohaberabi.medicinereminder.data.receiver.MedicineReminderReceiver
import com.mohaberabi.medicinereminder.domain.model.MedicineReminderModel
import com.mohaberabi.medicinereminder.domain.model.toMillis
import com.mohaberabi.medicinereminder.domain.source.MedicineAlarmManager
import com.mohaberabi.medicinereminder.domain.util.AppConst


class AndroidMedicineAlarmManager(
    private val context: Context,
) : MedicineAlarmManager {
    private val alarmManager by lazy {
        context.getSystemService<AlarmManager>()!!
    }

    override fun createMedicineAlarm(
        reminder: MedicineReminderModel,
    ) {
        if (reminder.repeatEvery != null) {
            setRepeated(reminder)
        } else {
            setOneTime(reminder)
        }
    }

    private fun setOneTime(reminder: MedicineReminderModel) {
        try {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                reminder.remindAt,
                reminder.createPendingIntent(context)
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

    }

    private fun setRepeated(reminder: MedicineReminderModel) {
        try {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                reminder.remindAt,
                reminder.interval.toMillis(requireNotNull(reminder.repeatEvery)),
                reminder.createPendingIntent(context)
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

    }

    override fun cancelMedicineAlarm(
        reminder: MedicineReminderModel,
    ) = alarmManager.cancel(reminder.createPendingIntent(context))


}

private fun MedicineReminderModel.createPendingIntent(context: Context): PendingIntent {
    val intent = Intent(context, MedicineReminderReceiver::class.java).apply {
        putExtra(AppConst.REMINDER_TTL_KEY, title)
        putExtra(AppConst.REMINDER_BODY_KEY, "With dosage is $dosage")

    }
    val pending = PendingIntent.getBroadcast(
        context,
        remindAt.toInt(),
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
    return pending
}

