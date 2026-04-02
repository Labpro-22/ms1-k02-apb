package com.nimons360.util

/**
 * Sealed class yang merepresentasikan hasil operasi jaringan.
 */
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val code: Int? = null) : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
}

/**
 * Extension function untuk membungkus respons API.
 */
inline fun <reified T> handleApiResponse(
    execute: () -> retrofit2.Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            response.body()?.let {
                NetworkResult.Success(it)
            } ?: NetworkResult.Error("Empty response body", response.code())
        } else {
            // Check for 409 Unauthorized (token expired)
            if (response.code() == 409) {
                NetworkResult.Error("Session expired. Please login again.", 409)
            } else {
                NetworkResult.Error(response.message() ?: "Unknown error", response.code())
            }
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "Network error occurred")
    }
}
