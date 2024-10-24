package com.mohaberabi.medicinereminder.domain.source

import com.mohaberabi.medicinereminder.domain.model.MedicineReminderModel

interface MedicineAlarmManager {


    fun createMedicineAlarm(
        reminder: MedicineReminderModel,
    )

    fun cancelMedicineAlarm(
        reminder: MedicineReminderModel,
    )
}