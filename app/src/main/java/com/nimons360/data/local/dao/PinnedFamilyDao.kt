package com.nimons360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nimons360.data.local.entity.PinnedFamilyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object untuk pinned families.
 * Method untuk berinteraksi dengan tabel pinned_families.
 */
@Dao
interface PinnedFamilyDao {

    /**
     * Mengambil semua pinned families dalam bentuk Flow untuk pembaruan reaktif.
     */
    @Query("SELECT * FROM pinned_families ORDER BY pinnedAt DESC")
    fun getAllPinnedFamilies(): Flow<List<PinnedFamilyEntity>>

    /**
     * Mengambil semua ID pinned families.
     */
    @Query("SELECT familyId FROM pinned_families")
    suspend fun getAllPinnedFamilyIds(): List<Long>

    /**
     * Mengecek apakah family dipin.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM pinned_families WHERE familyId = :familyId)")
    suspend fun isFamilyPinned(familyId: Long): Boolean

    /**
     * Mengecek apakah family dipin dalam bentuk Flow.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM pinned_families WHERE familyId = :familyId)")
    fun isFamilyPinnedFlow(familyId: Long): Flow<Boolean>

    /**
     * Menandai (pin) family.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun pinFamily(family: PinnedFamilyEntity)

    /**
     * Menghapus pin pada family berdasarkan ID family.
     */
    @Query("DELETE FROM pinned_families WHERE familyId = :familyId")
    suspend fun unpinFamily(familyId: Long)

    /**
     * Menghapus family yang dipin.
     */
    @Delete
    suspend fun deleteFamily(family: PinnedFamilyEntity)

    /**
     * Menghapus semua family yang dipin.
     */
    @Query("DELETE FROM pinned_families")
    suspend fun clearAllPinnedFamilies()

    /**
     * Mengambil jumlah family yang dipin.
     */
    @Query("SELECT COUNT(*) FROM pinned_families")
    suspend fun getPinnedCount(): Int
}
