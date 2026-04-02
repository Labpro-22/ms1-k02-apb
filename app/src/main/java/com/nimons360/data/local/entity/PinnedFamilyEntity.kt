package com.nimons360.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity for storing pinned family information locally.
 * This is used to persist pinned families even when the app is closed.
 */
@Entity(tableName = "pinned_families")
data class PinnedFamilyEntity(
    @PrimaryKey
    val familyId: Long,
    val familyName: String,
    val iconUrl: String,
    val pinnedAt: Long = System.currentTimeMillis()
)
