package com.food_dev.domain.dto.local.model.merchant

import com.google.gson.annotations.SerializedName

class MerchantOperatingTime(
    @SerializedName("openHours") var openHours: String,
    @SerializedName("closedHours") var closedHours: String,
    @SerializedName("isTwentyFourHours") var isTwentyFourHours: Boolean
)