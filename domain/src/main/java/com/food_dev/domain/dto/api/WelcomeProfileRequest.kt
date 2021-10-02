package com.food_dev.domain.dto.api

import com.google.gson.annotations.SerializedName

data class WelcomeProfileRequest(
    @SerializedName("phoneNumberPrefix") var phoneNumberPrefix: String,
    @SerializedName("merchantPhone") var phoneNumber: String,
    @SerializedName("merchantWhatsapp") var whatsAppNumber: String,
    @SerializedName("firebaseToken") var firebaseToken: String
)