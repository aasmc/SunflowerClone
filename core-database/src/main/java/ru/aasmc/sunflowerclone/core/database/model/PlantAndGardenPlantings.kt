package ru.aasmc.sunflowerclone.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PlantAndGardenPlantings(
    @Embedded
    val plant: PlantEntity,
    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlantingEntity> = emptyList()
)
