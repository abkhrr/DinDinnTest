package com.food_dev.domain.prefs

import com.food_dev.utils.ext.constant.Const

interface AppPreferences {
    fun getCurrentLoginMode(): Int
    fun setCurrentLoginMode(mode: Const.LoggedInMode)
}