package com.nimons360.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nimons360.data.local.dao.PinnedFamilyDao
import com.nimons360.data.local.entity.PinnedFamilyEntity

/**
 * Room Database untuk Nimons360.
 */
@Database(
    entities = [PinnedFamilyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NimonsDatabase : RoomDatabase() {

    abstract fun pinnedFamilyDao(): PinnedFamilyDao

    companion object {
        private const val DATABASE_NAME = "nimons360_database"

        @Volatile
        private var INSTANCE: NimonsDatabase? = null

        fun getInstance(context: Context): NimonsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NimonsDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
