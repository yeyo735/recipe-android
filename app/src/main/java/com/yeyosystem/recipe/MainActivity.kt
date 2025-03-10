package com.yeyosystem.recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.yeyosystem.recipe.presentation.ui.tabbar.RecipeAdaptiveAppNavigation
import com.yeyosystem.recipe.presentation.ui.theme.RecipeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeTheme {
                //val navController = rememberNavController()
                //RecipeApp(navController = navController)
                //RecipeAdaptiveApp()
                RecipeAdaptiveAppNavigation()
            }
        }
    }
}
