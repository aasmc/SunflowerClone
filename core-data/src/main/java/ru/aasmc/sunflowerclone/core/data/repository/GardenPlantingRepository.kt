package ru.aasmc.sunflowerclone.core.data.repository

import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.database.model.PlantAndGardenPlantings
import ru.aasmc.sunflowerclone.core.model.data.GardenPlanting

interface GardenPlantingRepository {
    suspend fun createGardenPlanting(plantId: String)

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting)

    fun isPlanted(plantId: String): Flow<Boolean>

    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>
}