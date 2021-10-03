package com.food_dev.utils.base.navigation

import android.app.Activity
import android.content.Context

fun Activity.startMainActivity(activity: Activity) {
    val intent = sendActivityIntent(activity, "com.food_dev.dashboard.activity.ui.MainActivity")
    activity.finish()
    startActivity(intent)
}

fun Activity.startAuthActivity(activity: Activity) {
    val intent = sendActivityIntent(activity, "com.food_dev.auth.activity.ui.AuthActivity")
    activity.finish()
    startActivity(intent)
}