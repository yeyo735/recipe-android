package com.yeyosystem.recipe.data.repository

import com.yeyosystem.recipe.data.remote.RecipeApiService
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.domain.model.RecipeDetail
import com.yeyosystem.recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeApiService
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return apiService.getRecipes().map { it.toDomain() }
    }

    override suspend fun getRecipeDetail(recipeId: String): RecipeDetail {
        return apiService.getRecipeDetail(recipeId).toDomain()
    }
}
