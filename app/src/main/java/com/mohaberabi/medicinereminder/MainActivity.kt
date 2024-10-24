package com.mohaberabi.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohaberabi.medicinereminder.presentation.ReminderScreen
import com.mohaberabi.medicinereminder.ui.theme.MedicineReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicineReminderTheme {
                ReminderScreen()
            }
        }
    }
}

