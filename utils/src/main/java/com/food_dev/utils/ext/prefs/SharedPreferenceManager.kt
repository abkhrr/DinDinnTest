package com.food_dev.utils.ext.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREFS_KEY_USER_VISITED_ON_BOARDING = "user_visited_onboarding"
    }

    var userVisitedOnBoarding: Boolean
        get(): Boolean {
            return sharedPreferences.getBoolean(PREFS_KEY_USER_VISITED_ON_BOARDING, false)
        }
        set(value) {
            sharedPreferences.edit().putBoolean(PREFS_KEY_USER_VISITED_ON_BOARDING, value).apply()
        }
}