package com.mohaberabi.medicinereminder.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.mohaberabi.medicinereminder.MedicineReminderApp
import com.mohaberabi.medicinereminder.domain.model.MedicineReminderModel
import com.mohaberabi.medicinereminder.domain.model.RepeatInterval
import com.mohaberabi.medicinereminder.domain.util.isNotificationGranted
import com.mohaberabi.medicinereminder.domain.util.requiresNotificationPermission
import com.mohaberabi.medicinereminder.domain.util.toHoursMillis
import com.mohaberabi.medicinereminder.domain.util.toMinutesMillis
import com.mohaberabi.medicinereminder.ui.theme.MedicineReminderTheme
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ReminderScreen(modifier: Modifier = Modifier) {

    val alarmManager = remember {
        MedicineReminderApp.appModule.reminderAlarmManager
    }
    var title by remember {
        mutableStateOf("")
    }

    var timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
    )
    var dosage by remember {
        mutableStateOf("")
    }

    var interval by remember {
        mutableStateOf(RepeatInterval.None)
    }
    var repeats by remember {
        mutableStateOf("")
    }


    var showTimePicker by remember {

        mutableStateOf(true)
    }
    val hour by remember {
        mutableIntStateOf(timePickerState.hour)
    }

    val minute by remember {
        mutableIntStateOf(timePickerState.minute)
    }

    if (requiresNotificationPermission()) {
        val launcher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
            ) {

            }

        LaunchedEffect(key1 = Unit) {
            launcher.launch(
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                )
            )
        }
    }
    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {


            TextField(
                value = title,
                label = {
                    Text(text = "Title")
                },
                onValueChange = {
                    title = it
                },
            )
            TextField(
                value = dosage,
                label = {
                    Text(text = "Dosage")
                },
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        dosage = it
                    }

                },
            )


            TextField(
                value = repeats.toString(),
                label = {
                    Text(text = "Repeats")
                },
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        repeats = it
                    }

                },
            )



            FlowRow {

                RepeatInterval.entries.forEach {
                    AssistChip(
                        onClick = {
                            interval = it
                        },
                        label = {
                            Text(
                                text = it.name,
                                color = if (it == interval)
                                    MaterialTheme.colorScheme.primary
                                else Color.Gray
                            )
                        },
                    )

                }

            }
            TimePicker(
                state = timePickerState,
            )

            Button(
                onClick = {
                    val remindAt = hour.toLong().toHoursMillis + minute.toLong().toMinutesMillis
                    val reminder = MedicineReminderModel(
                        title = title,
                        dosage = dosage.toIntOrNull() ?: 0,
                        remindAt = remindAt,
                        interval = interval,
                        repeatEvery = if (repeats.toLong()==0L) null else repeats.toLong()
                    )
                    alarmManager.createMedicineAlarm(reminder)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save")
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun PReviewRemidnersScreen() {
    MedicineReminderTheme {

        ReminderScreen()
    }
}