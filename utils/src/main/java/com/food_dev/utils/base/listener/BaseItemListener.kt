package com.food_dev.utils.base.listener

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}