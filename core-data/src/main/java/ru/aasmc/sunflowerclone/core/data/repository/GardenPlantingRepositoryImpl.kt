package ru.aasmc.sunflowerclone.core.data.repository

import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.database.dao.GardenPlantingDao
import ru.aasmc.sunflowerclone.core.database.model.GardenPlantingEntity
import ru.aasmc.sunflowerclone.core.database.model.PlantAndGardenPlantings
import ru.aasmc.sunflowerclone.core.model.data.GardenPlanting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GardenPlantingRepositoryImpl @Inject constructor(
    private val gardenPlantingDao: GardenPlantingDao
) : GardenPlantingRepository {
    override suspend fun createGardenPlanting(plantId: String) {
        val gardenPlanting = GardenPlantingEntity(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    override suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        val entity = GardenPlantingEntity(
            plantId = gardenPlanting.plantId,
            plantDate = gardenPlanting.plantDate,
            lastWateringDate = gardenPlanting.lastWateringDate,
        ).apply {
            gardenPlantingId = gardenPlanting.gardenPlantingId
        }
        gardenPlantingDao.deleteGardenPlanting(entity)
    }

    override fun isPlanted(plantId: String): Flow<Boolean> =
        gardenPlantingDao.isPlanted(plantId)

    override fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>> =
        gardenPlantingDao.getPlantedGardens()

}