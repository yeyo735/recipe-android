package com.yeyosystem.recipe.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class RecipeHomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipeListDisplaysCorrectly() {
        composeTestRule.setContent {
            RecipeHomeScreen(onRecipeSelected = {})
        }

        composeTestRule.onNodeWithText("Recipe App").assertIsDisplayed()
        composeTestRule.onNodeWithText("Buscar receta").assertIsDisplayed()
    }
}