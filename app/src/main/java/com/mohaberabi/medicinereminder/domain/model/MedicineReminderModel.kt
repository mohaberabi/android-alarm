package com.mohaberabi.medicinereminder.domain.model

import com.mohaberabi.medicinereminder.domain.util.toDaysMillis
import com.mohaberabi.medicinereminder.domain.util.toHoursMillis
import com.mohaberabi.medicinereminder.domain.util.toMinutesMillis


data class MedicineReminderModel(
    val remindAt: Long,
    val title: String,
    val dosage: Int,
    val lastRemindAt: Long? = null,
    val interval: RepeatInterval = RepeatInterval.None,
    val repeatEvery: Long? = null
)


enum class RepeatInterval {
    None,
    Minute,
    Hour,
    Day,
}


fun RepeatInterval.toMillis(value: Long) = when (this) {
    RepeatInterval.None -> 0
    RepeatInterval.Minute -> value.toMinutesMillis
    RepeatInterval.Hour -> value.toHoursMillis
    RepeatInterval.Day -> value.toDaysMillis
}