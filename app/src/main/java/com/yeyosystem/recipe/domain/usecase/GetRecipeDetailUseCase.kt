package com.yeyosystem.recipe.domain.usecase

import com.yeyosystem.recipe.domain.model.RecipeDetail
import com.yeyosystem.recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: String): RecipeDetail {
        return repository.getRecipeDetail(recipeId)
    }
}
