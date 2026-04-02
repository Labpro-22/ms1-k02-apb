package com.nimons360.data.local

import com.nimons360.data.local.dao.PinnedFamilyDao
import com.nimons360.data.local.entity.PinnedFamilyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository untuk mengelola keluarga yang dipin.
 * Menyediakan API untuk layer UI dalam berinteraksi dengan data family yang dipin.
 */
class PinnedFamilyRepository(
    private val pinnedFamilyDao: PinnedFamilyDao
) {

    /**
     * Mengambil semua family yang dipin dalam bentuk Flow.
     */
    fun getAllPinnedFamilies(): Flow<List<PinnedFamilyEntity>> {
        return pinnedFamilyDao.getAllPinnedFamilies()
    }

    /**
     * Mengambil semua ID family yang dipin.
     */
    suspend fun getAllPinnedFamilyIds(): List<Long> {
        return pinnedFamilyDao.getAllPinnedFamilyIds()
    }

    /**
     * Mengecek apakah sebuah family sudah dipin.
     */
    suspend fun isFamilyPinned(familyId: Long): Boolean {
        return pinnedFamilyDao.isFamilyPinned(familyId)
    }

    /**
     * Mengecek apakah sebuah family sudah dipin dalam bentuk Flow.
     */
    fun isFamilyPinnedFlow(familyId: Long): Flow<Boolean> {
        return pinnedFamilyDao.isFamilyPinnedFlow(familyId)
    }

    /**
     * Mengubah (toggle) status pin sebuah family.
     * Mengembalikan true jika family sekarang dipin.
     */
    suspend fun togglePin(familyId: Long, familyName: String, iconUrl: String): Boolean {
        return if (isFamilyPinned(familyId)) {
            unpinFamily(familyId)
            false
        } else {
            pinFamily(familyId, familyName, iconUrl)
            true
        }
    }

    /**
     * Menandai (pin) sebuah family.
     */
    private suspend fun pinFamily(familyId: Long, familyName: String, iconUrl: String) {
        val entity = PinnedFamilyEntity(
            familyId = familyId,
            familyName = familyName,
            iconUrl = iconUrl
        )
        pinnedFamilyDao.pinFamily(entity)
    }

    /**
     * Melepas pin dari sebuah family.
     */
    private suspend fun unpinFamily(familyId: Long) {
        pinnedFamilyDao.unpinFamily(familyId)
    }

    /**
     * Menghapus semua family yang dipin.
     */
    suspend fun clearAllPinned() {
        pinnedFamilyDao.clearAllPinnedFamilies()
    }

    /**
     * Mengambil jumlah family yang dipin.
     */
    suspend fun getPinnedCount(): Int {
        return pinnedFamilyDao.getPinnedCount()
    }
}
