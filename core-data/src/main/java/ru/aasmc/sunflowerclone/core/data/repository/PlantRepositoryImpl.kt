package ru.aasmc.sunflowerclone.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.aasmc.sunflowerclone.core.database.dao.PlantDao
import ru.aasmc.sunflowerclone.core.database.model.asDomainModel
import ru.aasmc.sunflowerclone.core.model.data.Plant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao
) : PlantRepository {
    override fun getPlants(): Flow<List<Plant>> = plantDao.getPlants().map { entities ->
        entities.map { plantEntity ->
            plantEntity.asDomainModel()
        }
    }

    override fun getPlant(plantId: String): Flow<Plant> = plantDao.getPlant(plantId)
        .map { plantEntity ->
            plantEntity.asDomainModel()
        }

    override fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Plant>> =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)
            .map { plantList ->
                plantList.map { plantEntity ->
                    plantEntity.asDomainModel()
                }
            }

}