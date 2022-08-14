package ru.aasmc.sunflowerclone.core.database.util

import ru.aasmc.sunflowerclone.core.database.model.GardenPlantingEntity
import ru.aasmc.sunflowerclone.core.database.model.PlantEntity
import ru.aasmc.sunflowerclone.core.model.data.GardenPlanting
import java.util.*

/**
 * [PlantEntity] objects used for tests.
 */
val testPlants = arrayListOf(
    PlantEntity("1", "Apple", "A red fruit", 1),
    PlantEntity("2", "B", "Description B", 1),
    PlantEntity("3", "C", "Description C", 2)
)
val testPlant = testPlants[0]

/**
 * [Calendar] object used for tests.
 */
val testCalendar: Calendar = Calendar.getInstance().apply {
    set(Calendar.YEAR, 1998)
    set(Calendar.MONTH, Calendar.SEPTEMBER)
    set(Calendar.DAY_OF_MONTH, 4)
}

/**
 * [GardenPlanting] object used for tests.
 */
val testGardenPlanting = GardenPlantingEntity(testPlant.plantId, testCalendar, testCalendar)