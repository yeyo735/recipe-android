package com.yeyosystem.recipe.di

import com.yeyosystem.recipe.domain.usecase.GetRecipeDetailUseCase
import com.yeyosystem.recipe.domain.usecase.GetRecipesUseCase
import com.yeyosystem.recipe.presentation.viewmodel.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideRecipeViewModel(
        getRecipesUseCase: GetRecipesUseCase,
        getRecipeDetailUseCase: GetRecipeDetailUseCase
    ): RecipeViewModel {
        return RecipeViewModel(getRecipesUseCase, getRecipeDetailUseCase)
    }
}
