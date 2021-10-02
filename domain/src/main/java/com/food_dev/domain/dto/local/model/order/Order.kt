package com.food_dev.domain.dto.local.model.order

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.address.MerchantAddress
import com.food_dev.domain.dto.local.model.address.UserAddress
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Order(
    @SerializedName("_id") var id: Int,
    @SerializedName("merchantId") var merchantId: String,
    @SerializedName("userId") var userId: String,
    @SerializedName("deliveryTo") var deliveryTo: UserAddress,
    @SerializedName("deliveryFrom") var deliveryFrom: MerchantAddress,
    @SerializedName("totalPrice") var totalPrice: Int,
    @SerializedName("shipmentPrice") var shipmentPrice: Int,
    @SerializedName("paymentMethod") var paymentMethod: String,
    @SerializedName("orderStatus") var orderStatus: OrderStatus,
    @SerializedName("created_at") var createdAt: Date,
    @SerializedName("alerted_at") var alertedAt: Date,
    @SerializedName("expired_at") var expiredAt: Date,
    @SerializedName("totalItems") var totalItems: Int,
    @SerializedName("orderItems") var orderItems: List<OrderItems>
): Parcelable