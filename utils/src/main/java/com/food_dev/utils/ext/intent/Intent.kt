package com.food_dev.utils.ext.intent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Context.intentTo(c: Class<*>, extIntent: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, c)
    extIntent?.invoke(intent)
    startActivity(intent)
}

fun Context.intentTo(c: String, extIntent: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, Class.forName(c))
    extIntent?.invoke(intent)
    startActivity(intent)
}

fun <T: View> Activity.findLazy(@IdRes id: Int): Lazy<T> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        findViewById(id)
    }
}

fun Activity.intExtras(key: String): Lazy<Int> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getIntExtra(key, 0)
    }
}

fun Activity.longExtras(key: String): Lazy<Long> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getLongExtra(key, 0L)
    }
}

fun Activity.booleanExtras(key: String): Lazy<Boolean> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getBooleanExtra(key, false)
    }
}

fun Activity.parcelExtras(key: String): Lazy<Parcelable?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            intent.getParcelableExtra(key) as Parcelable?
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}

fun Activity.doubleExtras(key: String): Lazy<Double> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        intent.getDoubleExtra(key, 0.0)
    }
}

fun Fragment.stringExtras(key: String): Lazy<String?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getString(key)
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("String extras not found")
        }
    }
}

fun Fragment.intExtras(key: String): Lazy<Int?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getInt(key, 0)
    }
}

fun Fragment.longExtras(key: String): Lazy<Long?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getLong(key, 0L)
    }
}

fun Fragment.booleanExtras(key: String): Lazy<Boolean?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getBoolean(key, false)
    }
}

fun <T>Fragment.parcelExtras(key: String): Lazy<T?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getParcelable(key) as T?
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}

fun Fragment.doubleExtras(key: String): Lazy<Double?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        arguments?.getDouble(key, 0.0)
    }
}

fun <T: Parcelable>Fragment.parcelArrayListExtras(key: String): Lazy<ArrayList<T>?> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        try {
            arguments?.getParcelableArrayList(key)
        } catch (e: NullPointerException) {
            throw java.lang.NullPointerException("Parcel extras not found")
        }
    }
}