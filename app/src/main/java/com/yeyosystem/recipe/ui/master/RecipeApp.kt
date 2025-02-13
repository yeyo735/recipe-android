package com.yeyosystem.recipe.ui.master

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yeyosystem.recipe.ui.detail.RecipeDetailScreen
import com.yeyosystem.recipe.ui.home.RecipeHomeScreen

@Composable
fun RecipeApp(navController: NavHostController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp // Determina si es tablet o pantalla ancha

        if (isTablet) {
            val recipeIdDetail = remember { mutableStateOf(0) }
            // Diseño List-Detail en una sola pantalla
            Row(modifier = Modifier.fillMaxSize()) {
                RecipeHomeScreen(
                    modifier = Modifier.weight(1f),
                    onRecipeSelected = { recipeId ->
                        navController.navigate("detail/$recipeId")
                        recipeIdDetail.value = recipeId.toInt()
                    }
                )

                RecipeDetailScreen(
                    modifier = Modifier.weight(2f),
                    recipeId = recipeIdDetail.toString()
                )
            }
        } else {
            // Diseño normal con navegación entre pantallas
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    RecipeHomeScreen(modifier = Modifier, onRecipeSelected =  { recipeId ->
                        navController.navigate("detail/$recipeId")
                    })
                }
                composable("detail/{recipeId}") { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")
                    if (recipeId != null) {
                        RecipeDetailScreen(modifier = Modifier, recipeId)
                    }
                }
            }
        }
    }
}
