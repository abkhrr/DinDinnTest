package com.food_dev.domain.dto.local.model.order

import android.os.Parcelable
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.domain.dto.local.model.ingredient.addon.Addons
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItems(
    @SerializedName("itemId") var itemId: Ingredient,
    @SerializedName("quantity") var quantity: Int,
    @SerializedName("price") var price: Int,
    @SerializedName("orderNotes") var notes: Int,
    @SerializedName("addons") var addon: List<OrderAddon>
): Parcelable