package com.food_dev.domain.dto.api

import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.google.gson.annotations.SerializedName

class ShowcaseIngredientResponse(
    @SerializedName("_id") var id: Int,
    @SerializedName("indgridients") var ingredients: List<Ingredient>,
)