package com.food_dev.utils.ext.view

import android.app.Activity
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AnimRes
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.food_dev.utils.ext.context.inputManager
import com.food_dev.utils.ext.context.loadAnimation
import com.food_dev.utils.ext.intent.intentTo
import com.food_dev.utils.ext.resultState.ResultState
import com.food_dev.utils.R
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun View.slideDown() {
    animate().setDuration(600).translationY(dip(height) * 1f).start()
}

fun View.slideUp() {
    animate().setDuration(600).translationY(16f).start()
}

fun View.showKeyboard() {
    clearFocus()
    requestFocus()
    context.inputManager?.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.closeKeyboard() {
    clearFocus()
    context.inputManager?.hideSoftInputFromWindow(windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun View?.backgroundColor(hexCode: String) {
    this?.run { setBackgroundColor(Color.parseColor(hexCode)) }
}

fun View?.backgroundColor(resId: Int) {
    this?.run { setBackgroundColor(ContextCompat.getColor(context, resId)) }
}

fun View?.onItemClicked() {
    this?.let {
        setOnClickListener { _ ->
            isEnabled = false
            postDelayed({ isEnabled = true }, 1000)
        }
    }
}

fun View.visible(animate: Boolean = true) {
    if (!animate) {
        visibility = View.VISIBLE
        alpha = 1.0f
        return
    }

    alpha = 0f
    visibility = View.VISIBLE
    animate().alpha(1.0f).duration = 100
}

fun View.invisible(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler(Looper.getMainLooper()).postDelayed({
        visibility = View.INVISIBLE
    }, delayMillis)
}

fun View.gone(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler(Looper.getMainLooper()).postDelayed({
        visibility = View.GONE
    }, delayMillis)
}

fun View?.toggleView(isTrue: Boolean, isGone: Boolean = true) {
    this?.let {
        if (isTrue) {
            visible()
        } else {
            if (isGone) {
                gone()
            } else {
                invisible()
            }
        }
    }
}

fun View.revealWithAnimation(@AnimRes resId: Int = R.anim.nav_default_exit_anim) {
    if (!isVisible) {
        isVisible = true
        startAnimation(context.loadAnimation(resId))
    }
}

fun View.hideWithAnimation(@AnimRes resId: Int = R.anim.nav_default_exit_anim, invisible: Boolean = false) {
    if (isVisible) {
        startAnimation(context.loadAnimation(resId))

        if (invisible) {
            isInvisible = true
        } else {
            isVisible = false
        }
    }
}

fun ViewGroup.inflate(layoutResId: Int): View = LayoutInflater.from(context).inflate(
    layoutResId,
    this,
    false
)

fun Activity.snack(msg: String) {
    val contentView = findViewById<View>(android.R.id.content)
    Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.snack(msg: String) {
    val contentView = view?.findViewById<View>(android.R.id.content)
    if (contentView != null) Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT).show()

}

fun Long.toBytesReadable() = when {
    this == Long.MIN_VALUE || this < 0 -> "N/A"
    this < 1024L -> "$this B"
    this <= 0xfffccccccccccccL shr 40 -> "%.1f KB".format(this.toDouble() / (0x1 shl 10))
    this <= 0xfffccccccccccccL shr 30 -> "%.1f MB".format(this.toDouble() / (0x1 shl 20))
    this <= 0xfffccccccccccccL shr 20 -> "%.1f GB".format(this.toDouble() / (0x1 shl 30))
    this <= 0xfffccccccccccccL shr 10 -> "%.1f TB".format(this.toDouble() / (0x1 shl 40))
    this <= 0xfffccccccccccccL -> "%.1f PiB".format((this shr 10).toDouble() / (0x1 shl 40))
    else -> "%.1f EiB".format((this shr 20).toDouble() / (0x1 shl 40))
}

fun Long.toSumReadable(): String? {
    if (this < 1000) return "" + this
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.0f%c",
        this / 1000.0.pow(exp),
        "kMGTPE"[exp - 1]
    )
}

fun <T : Any> ResultState<T>.bindToProgressView(view: View) {
    view.isVisible = this is ResultState.Loading
}

fun String.capital() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

infix fun View.detailFor(packageName: String?) {
    setOnClickListener {
        context.intentTo("com.utsman.detail.ui.DetailActivity") {
            putExtra("package_name", packageName)
        }
    }
}