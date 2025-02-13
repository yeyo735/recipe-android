package com.yeyosystem.recipe.data.remote

import android.util.Log
import com.yeyosystem.recipe.data.model.RecipeDetailDto
import com.yeyosystem.recipe.data.model.RecipeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeApiService @Inject constructor(private val client: HttpClient) {

    suspend fun getRecipes(): List<RecipeDto> = withContext(Dispatchers.IO) {
        return@withContext try {
            client.get("/recipes").body<List<RecipeDto>>() // Ktor will use Gson to deserialize
        } catch (e: Exception) {
            Log.e("RecipeApiService", "Error al obtener recetas", e)
            emptyList()
        }
    }

    suspend fun getRecipeDetail(recipeId: String): RecipeDetailDto = withContext(Dispatchers.IO) {
        val response: HttpResponse = client.get("/detail/$recipeId")
        val json = response.bodyAsText()
        println("WireMock Response: $json") // Depuración en consola

        return@withContext try {
            response.body()
        } catch (e: Exception) {
            println("Error parsing JSON: ${e.message}")
            throw e
        }
    }
}
