package com.food_dev.utils.ext.countdown

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.TextView
import com.food_dev.utils.ext.common.launchDelayedFunction
import com.food_dev.utils.ext.constant.Const.ONE_SECOND
import com.food_dev.utils.ext.date.formatIntoMinAndSeconds
import com.food_dev.utils.widget.ProgressStepBar
import com.google.android.material.button.MaterialButton

internal class CountDownRunnable(private val context: Context, private val handler: Handler): Runnable {

    var _expiresIn: Long = 0
    var _alertIn: Long = 0
    lateinit var _timeLeftView: TextView
    lateinit var _autoRejectView: TextView
    lateinit var _acceptButton: MaterialButton
    lateinit var _progressBar: ProgressStepBar

    companion object {
        private val TAG = CountDownRunnable::class.java.simpleName
    }

    fun setElapsed(expiryMillis: Long, alertMillis: Long) {
        _expiresIn = expiryMillis
        _alertIn = alertMillis/ONE_SECOND
    }

    fun setViews(timeLeftView: TextView, autoRejectView: TextView,
                 acceptButton: MaterialButton, progressBar: ProgressStepBar) {
        _timeLeftView = timeLeftView
        _autoRejectView = autoRejectView
        _acceptButton = acceptButton
        _progressBar = progressBar
    }

    private fun updateProgressBar(seconds: Long) {
        when {
            seconds >= 240 -> {
                _progressBar.step = 5
            }
            seconds in 180..239 -> {
                _progressBar.step = 4
            }
            seconds in 120..179 -> {
                _progressBar.step = 3
            }
            seconds in 60..119 -> {
                _progressBar.step = 2
            }
            seconds in 1..59 -> {
                _progressBar.step = 1
            } else -> {
            _progressBar.step = 0
        }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun run() {
        if(_expiresIn >= 0) {
            val secondsRemaining = _expiresIn/ONE_SECOND
            _timeLeftView.text = formatIntoMinAndSeconds(secondsRemaining)

            updateProgressBar(secondsRemaining)
            if(_alertIn == secondsRemaining) {
                val alertPlayer = MediaPlayer
                    .create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)
                alertPlayer.start()
                handler.postDelayed({
                    alertPlayer.stop()
                    alertPlayer.reset()
                    alertPlayer.release()
                }, 5000)
            }

            _expiresIn -= ONE_SECOND
            handler.postDelayed(this, ONE_SECOND)
            launchDelayedFunction(ONE_SECOND){}
        } else {
            _autoRejectView.visibility = View.INVISIBLE
            _timeLeftView.visibility = View.INVISIBLE
            _acceptButton.text = "Expired"
        }
    }
}