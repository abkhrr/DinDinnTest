package com.food_dev.domain.dto.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("firebaseToken") var firebaseToken: String
)