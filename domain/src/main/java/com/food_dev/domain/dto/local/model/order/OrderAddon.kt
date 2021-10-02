package com.food_dev.domain.dto.local.model.order

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.ingredient.addon.Addons
import com.food_dev.domain.dto.local.model.ingredient.addon.AddonsOption
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderAddon(
    @SerializedName("addonId") var addonId: Addons,
    @SerializedName("addonOptionId") var addonOptionId: AddonsOption,
    @SerializedName("price") var price: Int,
    @SerializedName("quantity") var quantity: Int
): Parcelable