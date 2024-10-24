package com.mohaberabi.medicinereminder.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <VM : ViewModel> viewmodelFactory(
    block: () -> VM
): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return block() as T
        }
    }
}

