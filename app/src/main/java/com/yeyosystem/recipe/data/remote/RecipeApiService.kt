package com.yeyosystem.recipe.data.remote

import com.yeyosystem.recipe.data.model.RecipeDetailDto
import com.yeyosystem.recipe.data.model.RecipeDto
import io.ktor.client.HttpClient
import javax.inject.Inject

class RecipeApiService @Inject constructor(private val client: HttpClient) {

    suspend fun getRecipes(): List<RecipeDto> {
        return client.safeGet("/recipes") ?: emptyList()
    }

    suspend fun getRecipeDetail(recipeId: String): RecipeDetailDto? {
        return client.safeGet("/recipes/$recipeId")
    }
}