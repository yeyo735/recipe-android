package com.yeyosystem.recipe.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend inline fun <reified T> HttpClient.safeGet(url: String): T? {
    return withContext(Dispatchers.IO) {
        try {
            val response: HttpResponse = get(url)
            response.body<T>() // Ktor will use Gson to deserialize
        } catch (e: ClientRequestException) {
            Log.e("HttpClient", "Client request error: ${e.message}")
            null
        } catch (e: ServerResponseException) {
            Log.e("HttpClient", "Server response error: ${e.message}")
            null
        } catch (e: HttpRequestTimeoutException) {
            Log.e("HttpClient", "Request timeout: ${e.message}")
            null
        } catch (e: IOException) {
            Log.e("HttpClient", "Network error: ${e.message}")
            null
        } catch (e: Exception) {
            Log.e("HttpClient", "Error: ${e.message}")
            null
        }
    }
}