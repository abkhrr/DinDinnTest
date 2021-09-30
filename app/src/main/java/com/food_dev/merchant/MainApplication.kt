package com.food_dev.merchant

import com.food_dev.utils.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: BaseApplication() {
    override fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}