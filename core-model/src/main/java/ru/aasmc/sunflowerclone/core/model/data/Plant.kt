package ru.aasmc.sunflowerclone.core.model.data

import java.util.*
import java.util.Calendar.DAY_OF_YEAR

data class Plant(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    /**
     * How often the plant should be watered in days
     */
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
) {
    /**
     * Determines if the plant should be watered. Returns true if [since]'s date >
     * date of last watering + wateringInterval, false otherwise
     */
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String = name
}
