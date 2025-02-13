package com.yeyosystem.recipe.domain.repository

import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.domain.model.RecipeDetail

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeDetail(recipeId: String): RecipeDetail
}
