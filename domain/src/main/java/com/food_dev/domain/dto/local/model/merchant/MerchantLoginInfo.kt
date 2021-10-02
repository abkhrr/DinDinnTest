package com.food_dev.domain.dto.local.model.merchant

import com.google.gson.annotations.SerializedName

data class MerchantLoginInfo(
    @SerializedName("email") var email: String
)