package com.food_dev.domain.dto.local.model.ingredient

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.ingredient.addon.Addons
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    @SerializedName("_id") var id: Int,
    @SerializedName("coreInfo") val coreInfo: IngredientCoreInfo,
    @SerializedName("priceInfo") val priceInfo: IngredientPriceInfo,
    @SerializedName("categoryInfo") val categoryInfo: IngredientCategoryInfo,
    @SerializedName("ownerId") val ownerId: String,
    @SerializedName("images") val images: List<IngredientImages>,
    @SerializedName("addon") val addon: List<Int>
): Parcelable