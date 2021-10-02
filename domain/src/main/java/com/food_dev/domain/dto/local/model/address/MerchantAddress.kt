package com.food_dev.domain.dto.local.model.address

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MerchantAddress(
    @SerializedName("_id") var id: String,
    @SerializedName("street") var street: String,
    @SerializedName("postalcode") var posCode: String,
    @SerializedName("fullAddress") var fullAddress: String,
    @SerializedName("addressName") var addressName: String,
    @SerializedName("latitude") var latitude: String,
    @SerializedName("longitude") var longitude: String,
    @SerializedName("nearestMrtStationId") var nearestMrtStationId: Int,
    @SerializedName("merchantId") var merchantId: String,
    @SerializedName("merchantPhoneNumber") var merchantPhoneNumber: String,
): Parcelable