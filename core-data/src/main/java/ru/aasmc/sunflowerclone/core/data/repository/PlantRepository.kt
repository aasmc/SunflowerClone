package ru.aasmc.sunflowerclone.core.data.repository

import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.model.data.Plant

interface PlantRepository {
    fun getPlants(): Flow<List<Plant>>

    fun getPlant(plantId: String): Flow<Plant>

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Plant>>
}