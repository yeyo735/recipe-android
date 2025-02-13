package com.yeyosystem.recipe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yeyosystem.recipe.domain.usecase.GetRecipesUseCase
import com.yeyosystem.recipe.domain.usecase.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    val recipes = liveData(Dispatchers.IO) {
        emit(getRecipesUseCase())
    }

    fun getRecipeDetail(recipeId: String) = liveData(Dispatchers.IO) {
        emit(getRecipeDetailUseCase(recipeId))
    }

}
