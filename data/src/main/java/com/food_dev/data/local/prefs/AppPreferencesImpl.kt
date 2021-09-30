package com.food_dev.data.local.prefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.food_dev.domain.prefs.AppPreferences
import com.food_dev.utils.ext.constant.Const
import com.food_dev.utils.ext.qualifiers.PreferenceInfo
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(context: Application, @PreferenceInfo private val prefName: String):
    AppPreferences {


    companion object{
        private const val PREF_KEY_LOGGED_IN_MODE            = "PREF_KEY_LOGIN_MODE"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun getCurrentLoginMode(): Int {
        return prefs.getInt(PREF_KEY_LOGGED_IN_MODE, Const.LoggedInMode.NOT_LOGGED_IN.type)
    }

    override fun setCurrentLoginMode(mode: Const.LoggedInMode) {
        return prefs.edit().putInt(PREF_KEY_LOGGED_IN_MODE, mode.type).apply()
    }


}