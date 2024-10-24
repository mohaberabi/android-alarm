package com.mohaberabi.medicinereminder.domain.util


val Long.toMinutesMillis get() = this * 60 * 1000L
val Long.toHoursMillis get() = this.toMinutesMillis * 60
val Long.toDaysMillis get() = toHoursMillis * 24
val Long.toWeeksMillis get() = toDaysMillis * 7



