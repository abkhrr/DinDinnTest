package com.food_dev.domain.dto.api

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("merchantName") var merchantName: String,
    @SerializedName("personInCharge") var personInCharge: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)