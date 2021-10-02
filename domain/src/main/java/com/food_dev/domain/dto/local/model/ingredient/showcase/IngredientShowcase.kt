package com.food_dev.domain.dto.local.model.ingredient.showcase

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientShowcase(
    @SerializedName("id") var id: Int,
    @SerializedName("ownerId") var merchantId: String,
    @SerializedName("showcaseTitle") var showcaseTitle: String,
    @SerializedName("ingredients") var ingredients: List<Ingredient>
): Parcelable