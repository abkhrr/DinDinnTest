package com.food_dev.domain.dto.local.model.ingredient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientCoreInfo(
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("availableQuantity") val availableQuantity : Int
): Parcelable