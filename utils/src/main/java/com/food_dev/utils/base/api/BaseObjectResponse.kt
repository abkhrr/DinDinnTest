package com.food_dev.utils.base.api

import com.google.gson.annotations.SerializedName

data class BaseObjectResponse<T>(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: T
)