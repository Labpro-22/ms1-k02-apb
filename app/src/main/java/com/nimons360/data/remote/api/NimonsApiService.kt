package com.nimons360.data.remote.api

import com.nimons360.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit service interface untuk Nimons360 API.
 */
interface NimonsApiService {

    // ========== Auth ==========

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponseData>>

    // ========== Profile ==========

    @GET("api/me")
    suspend fun getProfile(): Response<ApiResponse<UserDto>>

    @PATCH("api/me")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): Response<ApiResponse<UserDto>>

    // ========== Families ==========

    @GET("api/families")
    suspend fun getAllFamilies(): Response<ApiResponse<List<FamilyDto>>>

    @GET("api/me/families")
    suspend fun getMyFamilies(): Response<ApiResponse<List<MyFamilyDto>>>

    @GET("api/families/discover")
    suspend fun discoverFamilies(): Response<ApiResponse<List<DiscoverFamilyDto>>>

    @GET("api/families/{familyId}")
    suspend fun getFamilyDetail(@Path("familyId") familyId: Long): Response<ApiResponse<FamilyDetailDto>>

    @POST("api/families")
    suspend fun createFamily(@Body request: CreateFamilyRequest): Response<ApiResponse<FamilyDetailDto>>

    @POST("api/families/join")
    suspend fun joinFamily(@Body request: JoinFamilyRequest): Response<ApiResponse<JoinFamilyResponseData>>

    @POST("api/families/leave")
    suspend fun leaveFamily(@Body request: LeaveFamilyRequest): Response<ApiResponse<LeaveFamilyResponseData>>
}
