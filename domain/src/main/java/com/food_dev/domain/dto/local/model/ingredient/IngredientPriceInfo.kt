package com.food_dev.domain.dto.local.model.ingredient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientPriceInfo(
    @SerializedName("priceInt") val priceInt : Int,
    @SerializedName("priceText") val priceText : String
): Parcelable