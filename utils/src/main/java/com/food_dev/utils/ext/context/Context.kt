package com.food_dev.utils.ext.context

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.provider.OpenableColumns
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AnimRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.food_dev.utils.ext.properties.orFalse
import java.io.File
import android.app.Activity
import android.view.View
import com.food_dev.utils.ext.intent.intentTo
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context?.loadAnimation(@AnimRes resId: Int): Animation = AnimationUtils.loadAnimation(this, resId)

val Fragment.isNetworkAvailable: Boolean
    get() {
        return context?.isNetworkAvailable.orFalse()
    }

inline val Context.inputManager: InputMethodManager?
    get() {
        return ContextCompat.getSystemService(this, InputMethodManager::class.java)
    }

inline val Context.isNetworkAvailable: Boolean
    get() {
        val manager = ContextCompat.getSystemService(this, ConnectivityManager::class.java)
        val capabilities = manager?.getNetworkCapabilities(manager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

fun Context.copyToClipboard(data: String?) {
    ContextCompat.getSystemService(this, ClipboardManager::class.java)?.apply {
        if (data.isNullOrEmpty()) return
        setPrimaryClip(ClipData.newPlainText("Share", data.orEmpty()))
    }
}

@SuppressLint("Range")
fun Context.getFileName(uri: Uri): String {
    var filename = "image"

    if (uri.toString().startsWith("content://")) {
        var cursor: Cursor? = null

        try {
            cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                filename = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        } finally {
            cursor?.close()
        }
    } else if (uri.toString().startsWith("file://")) {
        filename = File(uri.toString()).name
    }

    return filename.apply {
        replace(Regex("[^a-zA-Z]+"), "")
    }
}

infix fun Context.detailFor(packageName: String?) {
    intentTo("com.utsman.detail.ui.DetailActivity") {
        putExtra("package_name", packageName)
    }
}

fun Context.getDrawableRes(drawableRes: Int, color: Int? = null): Drawable? {
    val originDrawable = ContextCompat.getDrawable(this, drawableRes)
    return if (color != null) {
        if (originDrawable != null) {
            val copyDrawable = DrawableCompat.wrap(originDrawable)
            DrawableCompat.setTint(copyDrawable, color)
            copyDrawable
        } else {
            originDrawable
        }
    } else {
        originDrawable
    }
}