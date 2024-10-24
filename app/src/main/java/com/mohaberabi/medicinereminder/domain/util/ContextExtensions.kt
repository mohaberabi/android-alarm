package com.mohaberabi.medicinereminder.domain.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat


fun Context.isPermissionGranted(
    permission: String
): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


fun requiresNotificationPermission(): Boolean = Build.VERSION.SDK_INT >= 33
fun Context.isNotificationGranted(): Boolean =
    if (requiresNotificationPermission()) {
        isPermissionGranted(Manifest.permission.POST_NOTIFICATIONS)
    } else true