package com.food_dev.domain.dto.local.model.ingredient.addon

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Addons(
    @SerializedName("id") var id: Int,
    @SerializedName("ingredientId") var ingredientId: Int,
    @SerializedName("title") var title: String,
    @SerializedName("optionType") var optionType: String,
    @SerializedName("addonOption") var addonOption: List<Int>,
): Parcelable