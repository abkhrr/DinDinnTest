package com.food_dev.domain.dto.local.model.ingredient.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientCategory(
    @SerializedName("_id") var id: Int,
    @SerializedName("categoryName") var categoryName: String
): Parcelable