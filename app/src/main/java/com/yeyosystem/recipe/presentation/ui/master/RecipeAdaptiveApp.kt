package com.yeyosystem.recipe.presentation.ui.master

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.presentation.providers.RecipeViewModel
import com.yeyosystem.recipe.presentation.ui.detailtab.RecipeDetailTabScreen
import com.yeyosystem.recipe.presentation.ui.home.RecipeHomeScreen


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun RecipeAdaptiveApp() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val selectedRecipe by viewModel.recipes.observeAsState(initial = null)
    var recipe: Recipe? by remember { mutableStateOf(null) }
    val recipeIdDetail = remember { mutableIntStateOf(0) }

    // Navigation for the list and detail panes
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 8.dp)
                ) {
                    RecipeHomeScreen(
                        modifier = Modifier
                            .fillMaxHeight(),
                        onRecipeSelected = { recipeId ->
                            recipe = selectedRecipe?.find { it.id == recipeId }
                            recipeIdDetail.intValue = recipeId.toInt()
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, recipeId)
                            //localNavController.navigate("detail/$recipeId")
                        },
                    )
                }
            }
            // List pane
        },
        detailPane = {
            AnimatedPane {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 8.dp)
                ) {
                    navigator.currentDestination?.content?.let { recipeId ->
                        recipe?.let { recipe ->
                            RecipeDetailTabScreen(
                                modifier = Modifier,
                                recipe = recipe,
                                recipeId = recipeId.toString()
                            )
                        } ?: CircularProgressIndicator()
                    }

                }
            }
            // Detail pane
        },
    )
}