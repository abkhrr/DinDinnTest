package com.food_dev.domain.dto.local.model.ingredient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientImages(
    @SerializedName("imageUrl") val imageUrl : String
): Parcelable