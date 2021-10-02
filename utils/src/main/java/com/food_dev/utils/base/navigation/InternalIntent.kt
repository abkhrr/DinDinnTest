package com.food_dev.utils.base.navigation

import android.content.Context
import android.content.Intent

fun sendActivityIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)