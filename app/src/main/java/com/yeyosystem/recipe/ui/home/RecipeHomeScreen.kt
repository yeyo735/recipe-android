package com.yeyosystem.recipe.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.yeyosystem.recipe.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeHomeScreen(
    modifier: Modifier = Modifier,
    onRecipeSelected: (String) -> Unit,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }
    val filteredRecipes by remember { derivedStateOf { recipes.filter { it.name.contains(searchQuery, ignoreCase = true) } } }

    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(query = searchQuery, onQueryChange = { searchQuery = it })

        LazyColumn {
            items(filteredRecipes) { recipe ->
                RecipeItem(recipe = recipe, onClick = { onRecipeSelected(recipe.id) })
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Buscar receta") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Close the keyboard when done
            }
        )
    )
}

@Composable
fun RecipeItem(recipe: com.yeyosystem.recipe.domain.model.Recipe, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(120.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(recipe.picture),
            contentDescription = recipe.name,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            RecipeName(recipe = recipe)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun RecipeName(recipe: com.yeyosystem.recipe.domain.model.Recipe) {
    Text(text = recipe.name, style = MaterialTheme.typography.headlineLarge)
}