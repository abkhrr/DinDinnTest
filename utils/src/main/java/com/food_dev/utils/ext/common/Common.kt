package com.food_dev.utils.ext.common

import android.os.Handler
import android.os.Looper

inline fun <T> T.applyIf(predicate: Boolean, block: T.() -> Unit): T = apply {
    if (predicate) block(this)
}

fun launchDelayedFunction(timeMillis: Long = 500, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, timeMillis)
}

fun forceClose() {
    android.os.Process.killProcess(android.os.Process.myPid())
}