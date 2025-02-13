package com.yeyosystem.recipe.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val picture: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
)