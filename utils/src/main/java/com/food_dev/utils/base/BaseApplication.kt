package com.food_dev.utils.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseApplication: Application() {

    abstract fun getBaseUrl(): String

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}