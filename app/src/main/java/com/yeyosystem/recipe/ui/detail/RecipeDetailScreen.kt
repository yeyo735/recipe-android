package com.yeyosystem.recipe.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeyosystem.recipe.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipeId: String,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipeDetail by viewModel.getRecipeDetail(recipeId).observeAsState()

    recipeDetail?.let { detail ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = detail.description, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ingredientes:", style = MaterialTheme.typography.titleSmall)
            detail.ingredients.forEach { ingredient ->
                Text(text = "- ${ingredient.name}: ${ingredient.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Preparación:", style = MaterialTheme.typography.titleSmall)
            Text(text = detail.preparation)
        }
    } ?: CircularProgressIndicator()
}
