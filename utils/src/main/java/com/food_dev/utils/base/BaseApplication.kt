package com.food_dev.utils.base

import android.app.Application
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseApplication: Application() {
    abstract fun getBaseUrl(): String
}