package com.yeyosystem.recipe.data.model

import com.google.gson.annotations.SerializedName
import com.yeyosystem.recipe.domain.model.RecipeDetail

data class RecipeDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("preparation") val preparation: String,
    @SerializedName("ingredients") val ingredients: List<IngredientDto>
) {
    fun toDomain(): RecipeDetail {
        return RecipeDetail(
            id = id.toString(),
            description = description,
            preparation = preparation,
            ingredients = ingredients.map { it.toDomain() }
        )
    }
}


