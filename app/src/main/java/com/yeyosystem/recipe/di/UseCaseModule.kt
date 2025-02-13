package com.yeyosystem.recipe.di

import com.yeyosystem.recipe.domain.repository.RecipeRepository
import com.yeyosystem.recipe.domain.usecase.GetRecipesUseCase
import com.yeyosystem.recipe.domain.usecase.GetRecipeDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetRecipesUseCase(repository: RecipeRepository) = GetRecipesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetRecipeDetailUseCase(repository: RecipeRepository) = GetRecipeDetailUseCase(repository)
}
