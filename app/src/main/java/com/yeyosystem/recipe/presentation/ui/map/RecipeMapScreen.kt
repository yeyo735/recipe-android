package com.yeyosystem.recipe.presentation.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.yeyosystem.recipe.domain.model.Recipe

@Composable
fun RecipeMapScreen(recipe: Recipe) {
    Box(modifier = Modifier.fillMaxSize()) {
        val marker = LatLng(recipe.latitude, recipe.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(marker, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { /* handle map click */ }
        ) {
            Marker(
                state = rememberMarkerState(position = marker),
                title = recipe.name
            )
        }

    }
}


