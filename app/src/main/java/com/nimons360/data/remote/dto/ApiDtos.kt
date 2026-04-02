package com.nimons360.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Generic API response wrapper.
 */
data class ApiResponse<T>(
    @SerializedName("data")
    val data: T?,
    @SerializedName("error")
    val error: String? = null
)

/**
 * Body request untuk login.
 */
data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)

/**
 * Data respons login.
 */
data class LoginResponseData(
    @SerializedName("token")
    val token: String,
    @SerializedName("expiresAt")
    val expiresAt: String,
    @SerializedName("user")
    val user: UserDto
)

/**
 * Data transfer object untuk user.
 */
data class UserDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("nim")
    val nim: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)

/**
 * Request untuk memperbarui profil.
 */
data class UpdateProfileRequest(
    @SerializedName("fullName")
    val fullName: String
)

/**
 * Data transfer object untuk Family.
 */
data class FamilyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("iconUrl")
    val iconUrl: String
)

/**
 * Data transfer object untuk Member.
 */
data class MemberDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("joinedAt")
    val joinedAt: String? = null
)

/**
 * Data transfer object untuk My Family (dengan anggota dan kode family).
 */
data class MyFamilyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("familyCode")
    val familyCode: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("members")
    val members: List<MemberDto>
)

/**
 * Data transfer object untuk Discover Family.
 */
data class DiscoverFamilyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("members")
    val members: List<MemberDto>
)

/**
 * Data transfer object untuk detail Family.
 */
data class FamilyDetailDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("isMember")
    val isMember: Boolean,
    @SerializedName("familyCode")
    val familyCode: String? = null,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("members")
    val members: List<MemberDto>
)

/**
 * Request untuk membuat family.
 */
data class CreateFamilyRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("iconUrl")
    val iconUrl: String
)

/**
 * Request untuk bergabung ke family.
 */
data class JoinFamilyRequest(
    @SerializedName("familyId")
    val familyId: Long,
    @SerializedName("familyCode")
    val familyCode: String
)

/**
 * Data respons bergabung ke family.
 */
data class JoinFamilyResponseData(
    @SerializedName("joined")
    val joined: Boolean
)

/**
 * Request untuk keluar dari family.
 */
data class LeaveFamilyRequest(
    @SerializedName("familyId")
    val familyId: Long
)

/**
 * Data respons keluar dari family.
 */
data class LeaveFamilyResponseData(
    @SerializedName("left")
    val left: Boolean
)
