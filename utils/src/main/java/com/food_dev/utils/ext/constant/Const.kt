package com.food_dev.utils.ext.constant

object Const {

    const val PREFS_NAME = "foo_dev_prefs"

    const val ONE_SECOND: Long = 1000
    const val DATE_FORMAT      = "yyyy-MM-dd'T'HH:mm'Z'"
    const val TIME_FORMAT      = "HH:mm aa"

    enum class LoggedInMode constructor(val type: Int) {
        NOT_LOGGED_IN(1),
        LOGGED_IN(2),
    }

}