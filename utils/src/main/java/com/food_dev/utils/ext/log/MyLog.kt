package com.food_dev.utils.ext.log

import timber.log.Timber

object MyLog {

    private const val LOGGING = true

    fun d(tag: String, message: String) {
        if (LOGGING) {
            Timber.d(tag, message)
        }
    }

    fun e(tag: String, message: String) {
        if (LOGGING) {
            Timber.e(tag, message)
        }
    }

    fun v(tag: String, message: String) {
        if (LOGGING) {
            Timber.v(tag, message)
        }
    }

    fun i(tag: String, message: String) {
        if (LOGGING) {
            Timber.i(tag, message)
        }
    }

    fun w(tag: String, message: String) {
        if (LOGGING) {
            Timber.w(tag, message)
        }
    }

}