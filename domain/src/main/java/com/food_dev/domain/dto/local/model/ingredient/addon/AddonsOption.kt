package com.food_dev.domain.dto.local.model.ingredient.addon

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.ingredient.IngredientPriceInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddonsOption(
    @SerializedName("_id") var id: String,
    @SerializedName("addonId") var addonId: Int,
    @SerializedName("titleAnswer") var titleAnswer: String,
    @SerializedName("priceInfo") var priceInfo: IngredientPriceInfo,
    @SerializedName("isAvailable") var isAvailable: Boolean,
    @SerializedName("availableQuantity") var availableQuantity: Int,
): Parcelable