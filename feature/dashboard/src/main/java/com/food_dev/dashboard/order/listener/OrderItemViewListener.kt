package com.food_dev.dashboard.order.listener

class OrderItemViewListener(private val onAcceptClick: () -> Unit){
    fun clickOnAccept() = onAcceptClick.invoke()
}