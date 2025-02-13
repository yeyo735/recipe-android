package com.yeyosystem.recipe.domain.model

data class RecipeDetail(
    val id: String,
    val description: String,
    val preparation: String,
    val ingredients: List<Ingredient> = emptyList()
)