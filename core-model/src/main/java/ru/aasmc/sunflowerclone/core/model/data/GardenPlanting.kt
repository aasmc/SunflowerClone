package ru.aasmc.sunflowerclone.core.model.data

import java.util.*

data class GardenPlanting(
    val gardenPlantingId: Long = 0,
    val plantId: String,
    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    val plantDate: Calendar = Calendar.getInstance(),
    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    val lastWateringDate: Calendar = Calendar.getInstance()
)
