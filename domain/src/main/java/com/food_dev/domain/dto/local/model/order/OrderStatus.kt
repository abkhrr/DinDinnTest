package com.food_dev.domain.dto.local.model.order

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderStatus(
    @SerializedName("statusName") var statusName: String,
    @SerializedName("isOrderConfirmed") var isOrderConfirmed: Boolean,
    @SerializedName("isOrderPrepared") var isOrderPrepared: Boolean,
    @SerializedName("isOrderCompleted") var isOrderCompleted: Boolean,
    @SerializedName("isOrderExpired") var isOrderExpired: Boolean,
): Parcelable