package com.food_dev.merchant

import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.food_dev.merchant.notification.NotificationService
import com.food_dev.utils.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: BaseApplication() {

    override fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName = "FoodDevNoHeadsUp"
            val importance  = NotificationManager.IMPORTANCE_DEFAULT
            NotificationService().registerOtherChannel(this, channelName, importance)
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}