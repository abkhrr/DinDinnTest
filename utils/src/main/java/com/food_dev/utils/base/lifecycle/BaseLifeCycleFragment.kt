package com.food_dev.utils.base.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseLifeCycleFragment: Fragment(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    abstract fun onLifeCycleStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    abstract fun onLifeCycleStop()

}