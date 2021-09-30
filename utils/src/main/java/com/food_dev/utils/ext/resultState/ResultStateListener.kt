package com.food_dev.utils.ext.resultState

interface ResultStateListener<T: Any> {
    fun onIdle()
    fun onLoading()
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}