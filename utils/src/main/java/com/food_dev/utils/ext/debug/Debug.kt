package com.food_dev.utils.ext.debug

import com.food_dev.utils.BuildConfig

fun debug(action: () -> Unit) {
    if (BuildConfig.DEBUG) {
        action.invoke()
    }
}