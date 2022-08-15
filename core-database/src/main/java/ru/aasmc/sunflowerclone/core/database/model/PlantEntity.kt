package ru.aasmc.sunflowerclone.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aasmc.sunflowerclone.core.model.data.Plant

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
)

fun PlantEntity.asDomainModel(): Plant {
    return Plant(
        plantId = plantId,
        name = name,
        description = description,
        growZoneNumber = growZoneNumber,
        wateringInterval = wateringInterval,
        imageUrl = imageUrl
    )
}
