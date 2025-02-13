package com.yeyosystem.recipe.data.model

import com.google.gson.annotations.SerializedName
import com.yeyosystem.recipe.domain.model.Ingredient

data class IngredientDto(
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: String
) {
    fun toModel(): Ingredient {
        return Ingredient(
            name = name,
            quantity = quantity
        )
    }
}
