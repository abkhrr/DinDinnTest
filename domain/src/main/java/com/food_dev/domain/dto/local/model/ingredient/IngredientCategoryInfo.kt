package com.food_dev.domain.dto.local.model.ingredient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientCategoryInfo(
    @SerializedName("category") val category : Int,
    @SerializedName("subCategory") val subCategory : Int,
    @SerializedName("slashedCategory") val slashedCategory : String
): Parcelable