package com.food_dev.domain.dto.local.model.merchant

import com.google.gson.annotations.SerializedName

class MerchantCoreInfo(
    @SerializedName("merchantName") var merchantName: String,
    @SerializedName("phoneNumberPrefix") var phoneNumberPrefix: String,
    @SerializedName("merchantPhone") var merchantPhone: String,
    @SerializedName("merchantWhatsapp") var merchantWhatsapp: String,
    @SerializedName("personInCharge") var personInCharge: String,
    @SerializedName("joinedDate") var joinedDate: String,
    @SerializedName("isBranchMerchant") var isBranchMerchant: Boolean,
    @SerializedName("merchantImageUrl") var merchantImageUrl: String,
    @SerializedName("firebaseToken") var firebaseToken: String
)