package com.yeyosystem.recipe.data.repository

import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.domain.model.RecipeDetail
import com.yeyosystem.recipe.domain.repository.RecipeRepository
import com.yeyosystem.recipe.data.remote.RecipeApiService
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeApiService
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val recipes = apiService.getRecipes()
        return recipes.map { it.toDomain() }
    }

    override suspend fun getRecipeDetail(recipeId: String): RecipeDetail {
        val recipeDetail = apiService.getRecipeDetail(recipeId)
        return recipeDetail?.toDomain() ?: RecipeDetail("0", "", "", emptyList())
    }
}
