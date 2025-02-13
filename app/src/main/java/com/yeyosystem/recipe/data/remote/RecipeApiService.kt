package com.yeyosystem.recipe.data.remote

import com.yeyosystem.recipe.data.model.RecipeDetailDto
import com.yeyosystem.recipe.data.model.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {
    @GET("/recipes")
    suspend fun getRecipes(): List<RecipeDto>

    @GET("/recipes/{id}")
    suspend fun getRecipeDetail(@Path("id") recipeId: String): RecipeDetailDto
}
