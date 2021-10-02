package com.food_dev.domain.dto.api

import com.food_dev.domain.dto.local.model.merchant.MerchantCoreInfo
import com.food_dev.domain.dto.local.model.merchant.MerchantLoginInfo
import com.food_dev.domain.dto.local.model.merchant.MerchantOperatingTime
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("_id") var id: String,
    @SerializedName("merchantCoreInfo") var coreInfo: MerchantCoreInfo,
    @SerializedName("loginInfo") var loginInfo: MerchantLoginInfo,
    @SerializedName("operatingTime") var operatingTime: MerchantOperatingTime,
    @SerializedName("merchantCategory") var merchantCategory: String,
    @SerializedName("isAccountDeactivated") var isAccountDeactivated: Boolean
)