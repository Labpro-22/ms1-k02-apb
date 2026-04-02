package com.nimons360.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Manager sesi untuk mengelola token autentikasi dan sesi pengguna.
 * Menggunakan EncryptedSharedPreferences untuk penyimpanan data sensitif yang aman.
 */
class SessionManager(context: Context) {

    companion object {
        private const val PREFS_NAME = "nimons360_secure_prefs"
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_TOKEN_EXPIRY = "token_expiry"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NIM = "user_nim"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * Menyimpan token autentikasi.
     */
    fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    /**
     * Mengambil token autentikasi.
     */
    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    /**
     * Menyimpan waktu kedaluwarsa token.
     */
    fun saveTokenExpiry(expiry: String) {
        prefs.edit().putString(KEY_TOKEN_EXPIRY, expiry).apply()
    }

    /**
     * Mengecek apakah pengguna sudah login.
     */
    fun isLoggedIn(): Boolean {
        return getToken() != null
    }

    /**
     * Menghapus sesi (logout).
     */
    fun clearSession() {
        prefs.edit().clear().apply()
    }

    /**
     * Menyimpan informasi pengguna setelah login.
     */
    fun saveUserInfo(id: Long, nim: String, email: String, name: String) {
        prefs.edit()
            .putLong(KEY_USER_ID, id)
            .putString(KEY_USER_NIM, nim)
            .putString(KEY_USER_EMAIL, email)
            .putString(KEY_USER_NAME, name)
            .apply()
    }

    /**
     * Mengambil ID pengguna.
     */
    fun getUserId(): Long {
        return prefs.getLong(KEY_USER_ID, -1)
    }

    /**
     * Mengambil NIM pengguna.
     */
    fun getUserNim(): String? {
        return prefs.getString(KEY_USER_NIM, null)
    }

    /**
     * Mengambil email pengguna.
     */
    fun getUserEmail(): String? {
        return prefs.getString(KEY_USER_EMAIL, null)
    }

    /**
     * Mengambil nama pengguna.
     */
    fun getUserName(): String? {
        return prefs.getString(KEY_USER_NAME, null)
    }

    /**
     * Memperbarui nama pengguna.
     */
    fun updateUserName(name: String) {
        prefs.edit().putString(KEY_USER_NAME, name).apply()
    }

    /**
     * Mengambil nilai header authorization.
     */
    fun getAuthHeader(): String? {
        return getToken()?.let { "Bearer $it" }
    }
}
