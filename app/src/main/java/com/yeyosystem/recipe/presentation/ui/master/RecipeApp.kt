package com.yeyosystem.recipe.presentation.ui.master

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.presentation.providers.RecipeViewModel
import com.yeyosystem.recipe.presentation.ui.detailtab.RecipeDetailTabScreen
import com.yeyosystem.recipe.presentation.ui.home.RecipeHomeScreen

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RecipeApp(navController: NavHostController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val viewModel: RecipeViewModel = hiltViewModel()
        val selectedRecipe by viewModel.recipes.observeAsState(initial = null)
        var recipe: Recipe? by remember { mutableStateOf(null) }
        val isTablet = maxWidth > 600.dp // 600dp is the breakpoint for tablets
        if (isTablet) {
            val recipeIdDetail = remember { mutableIntStateOf(0) }
            Row(modifier = Modifier.fillMaxSize()) {
                val localNavController = rememberNavController()
                Box(modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)) {
                    RecipeHomeScreen(
                        modifier = Modifier
                            .fillMaxHeight(),
                        onRecipeSelected = { recipeId ->
                            recipe = selectedRecipe?.find { it.id == recipeId }
                            recipeIdDetail.intValue = recipeId.toInt()
                            localNavController.navigate("detail/$recipeId")
                        }
                    )
                }
                Box(modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)) {
                    NavHost(
                        localNavController,
                        startDestination = "detail/${recipeIdDetail.intValue}"
                    ) {
                        composable("detail/{recipeId}") { backStackEntry ->
                            val recipeId =
                                backStackEntry.arguments?.getString("recipeId") ?: return@composable

                            recipe?.let { recipe ->
                                RecipeDetailTabScreen(
                                    modifier = Modifier,
                                    recipe = recipe,
                                    recipeId = recipeId
                                )
                            } ?: CircularProgressIndicator()
                        }
                    }
                }
            }
        } else {
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    RecipeHomeScreen(
                        modifier = Modifier,
                        onRecipeSelected = { recipeId ->
                            viewModel.getRecipeDetail(recipeId) // ✅ Cargar detalles en móviles
                            navController.navigate("detail/$recipeId")
                        }
                    )
                }

                composable("detail/{recipeId}") { backStackEntry ->
                    val recipeId =
                        backStackEntry.arguments?.getString("recipeId") ?: return@composable

                    selectedRecipe?.find { it.id == recipeId }?.let { recipe ->
                        RecipeDetailTabScreen(
                            modifier = Modifier,
                            recipe = recipe,
                            recipeId = recipeId
                        )
                    } ?: CircularProgressIndicator()
                }
            }
        }
    }
}


