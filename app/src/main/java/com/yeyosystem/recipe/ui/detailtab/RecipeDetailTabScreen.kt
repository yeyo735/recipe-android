package com.yeyosystem.recipe.ui.detailtab

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.ui.appbar.RecipeAppBar
import com.yeyosystem.recipe.ui.detail.RecipeDetailScreen
import com.yeyosystem.recipe.ui.map.RecipeMapScreen


@Composable
fun RecipeDetailTabScreen(modifier: Modifier, recipe: Recipe, recipeId: String?) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { RecipeAppBar(title = recipe.name) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }) {
                    Text("Detail", modifier = Modifier.padding(16.dp))
                }
                Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }) {
                    Text("Map", modifier = Modifier.padding(16.dp))
                }
            }

            when (selectedTabIndex) {
                0 -> if (recipeId != null) {
                        RecipeDetailScreen(modifier = Modifier, recipe = recipe, recipeId = recipeId)

                }
                1 -> RecipeMapScreen(recipe)
            }
        }
    }
}
