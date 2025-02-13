package com.yeyosystem.recipe.di

import com.yeyosystem.recipe.data.repository.RecipeRepositoryImpl
import com.yeyosystem.recipe.domain.repository.RecipeRepository
import com.yeyosystem.recipe.data.remote.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: RecipeApiService): RecipeRepository {
        return RecipeRepositoryImpl(apiService)
    }
}