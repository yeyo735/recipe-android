package com.yeyosystem.recipe.presentation.ui.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    title: String,
) {
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
