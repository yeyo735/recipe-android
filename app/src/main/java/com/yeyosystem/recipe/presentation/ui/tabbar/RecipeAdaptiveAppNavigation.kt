package com.yeyosystem.recipe.presentation.ui.tabbar

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.window.core.layout.WindowWidthSizeClass
import com.yeyosystem.recipe.presentation.navigation.AppDestinations
import com.yeyosystem.recipe.presentation.ui.master.RecipeAdaptiveApp

@Composable
fun RecipeAdaptiveAppNavigation() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    // Get the current window adaptive information
    val adaptiveInfo = currentWindowAdaptiveInfo()

    val customNavSuiteType = with(adaptiveInfo) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.EXPANDED -> {
                NavigationSuiteType.NavigationDrawer
            }
            WindowWidthSizeClass.MEDIUM -> {
                NavigationSuiteType.NavigationRail
            }
            else -> {
                NavigationSuiteType.NavigationBar
            }
        }
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            destination.icon,
                            contentDescription = stringResource(destination.contentDescription)
                        )
                    },
                    label = { Text(stringResource(destination.label)) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination }
                )
            }
        },
        layoutType = customNavSuiteType // Usar el tipo de navegación adaptativa
    ) {
        // Contenido de la pantalla basado en la navegación actual
        when (currentDestination) {
            AppDestinations.HOME -> RecipeAdaptiveApp()
            AppDestinations.FAVORITES -> FavoritesDestination()
            AppDestinations.SHOPPING -> ShoppingDestination()
            AppDestinations.PROFILE -> ProfileDestination()
        }
    }
}

@Composable
fun FavoritesDestination() {
    // Contenido de la pantalla de favoritos
    Text("Favorites Screen")
}

@Composable
fun ShoppingDestination() {
    // Contenido de la pantalla de compras
    Text("Shopping Screen")
}

@Composable
fun ProfileDestination() {
    // Contenido de la pantalla de perfil
    Text("Profile Screen")
}