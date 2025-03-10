package com.yeyosystem.recipe.presentation.ui.detailtab

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.presentation.ui.appbar.RecipeAppBar
import com.yeyosystem.recipe.presentation.ui.detail.RecipeDetailScreen
import com.yeyosystem.recipe.presentation.ui.map.RecipeMapScreen


@Composable
fun RecipeDetailTabScreen(modifier: Modifier, recipe: Recipe, recipeId: String?) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { RecipeAppBar(title = recipe.name) }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues).fillMaxWidth().fillMaxHeight()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }) {
                    Text("Detail", modifier = modifier.padding(16.dp))
                }
                Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }) {
                    Text("Map", modifier = modifier.padding(16.dp))
                }
            }

            when (selectedTabIndex) {
                0 -> if (recipeId != null) {
                    RecipeDetailScreen(modifier = modifier, recipe = recipe, recipeId = recipeId)

                }
                1 -> RecipeMapScreen(recipe)
            }
        }
    }
}
