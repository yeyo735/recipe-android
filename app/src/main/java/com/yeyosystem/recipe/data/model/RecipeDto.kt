package com.yeyosystem.recipe.data.model

import com.google.gson.annotations.SerializedName
import com.yeyosystem.recipe.domain.model.Recipe

data class RecipeDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("location") val location: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
){

    fun toDomain(): Recipe {
        return Recipe(
            id = id,
            name = name,
            picture = picture,
            location = location,
            latitude = latitude.toDouble(),
            longitude = longitude.toDouble()
        )
    }
}

