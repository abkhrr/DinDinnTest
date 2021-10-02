package com.food_dev.dashboard.order.listener

import com.food_dev.domain.dto.local.model.order.Order

interface OrderClickListener {
    fun onAcceptClicked(order: Order)
}