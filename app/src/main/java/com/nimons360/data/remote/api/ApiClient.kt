package com.nimons360.data.remote.api

import com.google.gson.Gson
import com.nimons360.util.SessionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Factory API Client untuk Nimons360.
 * Mengonfigurasi OkHttp dengan auth interceptor dan membuat instance Retrofit.
 */
class ApiClient(private val sessionManager: SessionManager) {

    companion object {
        private const val BASE_URL = "https://mad.labpro.hmif.dev/"
        private const val TIMEOUT_SECONDS = 30L
    }

    private val gson = Gson()

    /**
     * Auth interceptor yang menambahkan header authorization ke setiap request.
     */
    private val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            // Skip penambahan header auth untuk endpoint login
            if (originalRequest.url.encodedPath.contains("/api/login")) {
                return chain.proceed(originalRequest)
            }

            val token = sessionManager.getToken()
            if (token == null) {
                return chain.proceed(originalRequest)
            }

            val authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            return chain.proceed(authenticatedRequest)
        }
    }

    /**
     * Interceptor logging untuk debugging.
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Konfigurasi client OkHttp.
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    /**
     * Instance Retrofit.
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /**
     * Instance API service.
     */
    val apiService: NimonsApiService = retrofit.create(NimonsApiService::class.java)
}
