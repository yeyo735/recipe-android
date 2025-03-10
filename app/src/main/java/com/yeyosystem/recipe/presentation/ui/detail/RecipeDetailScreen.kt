package com.yeyosystem.recipe.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.presentation.providers.RecipeViewModel

@Composable
fun RecipeDetailScreen(
    modifier: Modifier,
    recipe: Recipe,
    recipeId: String,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipeDetail by viewModel.getRecipeDetail(recipeId).observeAsState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        recipe.let {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card {
                    Image(
                        painter = rememberAsyncImagePainter(it.picture),
                        contentDescription = it.name,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

        }
        recipeDetail?.let { detail ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(detail.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(bottom = 16.dp))

                Card {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Ingredients:", style = MaterialTheme.typography.titleLarge)
                        detail.ingredients.forEach { ingredient ->
                            Text("- ${ingredient.name}: ${ingredient.quantity}")
                        }

                        Text("Preparation:", style = MaterialTheme.typography.titleLarge)
                        Text(detail.preparation, modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }
        } ?: CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
        recipe.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Origin:", style = MaterialTheme.typography.titleLarge)
                Text(
                    it.location,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

        }
    }
}
