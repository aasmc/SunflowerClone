package ru.aasmc.sunflowerclone.core.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.database.model.GardenPlantingEntity
import ru.aasmc.sunflowerclone.core.database.model.PlantAndGardenPlantings

/**
 * The Data Access Object for the [GardenPlanting] class.
 */
@Dao
interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): Flow<List<GardenPlantingEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM garden_plantings WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): Flow<Boolean>

    /**
     * This query will tell Room to query both the [PlantEntity] and [GardenPlantingEntity] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting: GardenPlantingEntity): Long

    @Delete
    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlantingEntity)
}