package com.yeyosystem.recipe.domain.usecase

import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipes().map { it }
    }
}
