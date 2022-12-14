package ru.aasmc.sunflowerclone.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import ru.aasmc.sunflowerclone.core.database.dao.GardenPlantingDao
import ru.aasmc.sunflowerclone.core.database.dao.PlantDao
import ru.aasmc.sunflowerclone.core.database.model.GardenPlantingEntity
import ru.aasmc.sunflowerclone.core.database.model.PlantEntity
import ru.aasmc.sunflowerclone.core.database.util.Converters
import ru.aasmc.sunflowerclone.core.database.worker.SeedDatabaseWorker
import ru.aasmc.sunflowerclone.core.database.worker.SeedDatabaseWorker.Companion.KEY_FILENAME

/**
 * The Room database for this app.
 */
@Database(
    entities = [GardenPlantingEntity::class, PlantEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
                                .addTag("SEED DATABASE")
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }

        const val DATABASE_NAME = "sunflowerclone_db"
        const val PLANT_DATA_FILENAME = "plants.json"
    }
}